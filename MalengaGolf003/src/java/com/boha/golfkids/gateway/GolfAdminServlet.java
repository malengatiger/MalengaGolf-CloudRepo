/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.gateway;

import com.boha.golfkids.data.GolfGroup;
import com.boha.golfkids.dto.AdministratorDTO;
import com.boha.golfkids.dto.AgeGroupDTO;
import com.boha.golfkids.dto.ClubDTO;
import com.boha.golfkids.dto.CountryDTO;
import com.boha.golfkids.dto.GolfGroupDTO;
import com.boha.golfkids.dto.LeaderBoardDTO;
import com.boha.golfkids.dto.ParentDTO;
import com.boha.golfkids.dto.PlayerDTO;
import com.boha.golfkids.dto.TournamentDTO;
import com.boha.golfkids.dto.RequestDTO;
import com.boha.golfkids.dto.ResponseDTO;
import com.boha.golfkids.util.DataException;
import com.boha.golfkids.util.DataUtil;
import com.boha.golfkids.util.GZipUtility;
import com.boha.golfkids.util.LeaderBoardUtil;
import com.google.gson.Gson;
import com.oreilly.servlet.ServletUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AubreyM
 */
@WebServlet(name = "GolfAdminServlet", urlPatterns = {"/golf"})
public class GolfAdminServlet extends HttpServlet {

    @EJB
    DataUtil dataUtil;
    @EJB
    LeaderBoardUtil leaderBoardUtil;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * Serves as the main administration servlet
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long start = System.currentTimeMillis();

        Gson gson = new Gson();
        ResponseDTO resp = new ResponseDTO();
        RequestDTO gr = getRequest(gson, request);
        //
        if (gr == null) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("Bad Request, nobody's home for you!");
            out.close();
        } else {

            try {
                switch (gr.getRequestCode()) {
                    case RequestDTO.GET_TEE_TIMES:
                        resp.setTeeTimeList(dataUtil.getTeeTimes(gr.getTournamentID()));
                        break;
                    case RequestDTO.GET_LEADERBOARD:
                        List<LeaderBoardDTO> bd = leaderBoardUtil.getLeaderBoard(gr.getTournamentID(),dataUtil);
                        resp.setLeaderBoard(bd);
                        break;
                    case RequestDTO.GET_LEADERBOARD_BOYS:
                        List<LeaderBoardDTO> bdb = leaderBoardUtil.getLeaderBoardBoys(gr.getTournamentID(),dataUtil);
                        resp.setLeaderBoard(bdb);
                        break;
                    case RequestDTO.GET_LEADERBOARD_GIRLS:
                        List<LeaderBoardDTO> bdg = leaderBoardUtil.getLeaderBoardGirls(gr.getTournamentID(),dataUtil);
                        resp.setLeaderBoard(bdg);
                        break;
                    case RequestDTO.UPDATE_TOURNAMENT_SCORES:
                        dataUtil.updateTournamentScoreByRound(gr.getTourneyScoreByRoundList());
                        break;
                    case RequestDTO.UPDATE_TOURNAMENT_SCORE_TOTALS:
                        dataUtil.updateTourneyPlayerScores(gr.getPlayers());
                        break;
                    case RequestDTO.ADD_TOURNAMENT_PLAYERS:
                        resp = dataUtil.addTournamentPlayers(gr.getPlayers());
                        break;
                    case RequestDTO.ADD_TEE_TIMES:
                        resp = dataUtil.addTeeTimes(gr.getTeeTimeList());
                        break;
                    case RequestDTO.UPDATE_TEE_TIMES:
                        dataUtil.updateTeeTimes(gr.getTeeTimeList());
                        break;
                    case RequestDTO.ADD_TOURNAMENT:
                        TournamentDTO t = dataUtil.addTournament(gr.getTournament());
                        resp.setTournaments(new ArrayList<TournamentDTO>());
                        resp.getTournaments().add(t);
                        break;
                    case RequestDTO.ADD_PLAYER:
                        List<PlayerDTO> pl = dataUtil.addPlayers(gr.getPlayerList(), gr.getGolfGroupID());
                        resp.setPlayers(pl);
                        break;
                    case RequestDTO.UPDATE_PLAYER:
                        dataUtil.updatePlayer(gr.getPlayerList().get(0));
                        break;

                    case RequestDTO.ADD_PARENT:
                        ParentDTO parent = dataUtil.addParent(gr.getParent(), gr.getGolfGroupID());
                        resp.setParents(new ArrayList<ParentDTO>());
                        resp.getParents().add(parent);
                        break;
                    case RequestDTO.UPDATE_PARENT:
                        dataUtil.updateParent(gr.getParent());
                        break;
                    case RequestDTO.ADMIN_LOGIN:
                        AdministratorDTO admin1 = dataUtil.getAdminLoggedIn(gr.getEmail(), gr.getPin());
                        resp.setAdministrators(new ArrayList<AdministratorDTO>());
                        resp.getAdministrators().add(admin1);
                        //TODO - is admin joining GolfGroup on new device? then get golf data ...
                        //must request zipped response
                        GolfGroup gg = dataUtil.getGroupByID(gr.getGolfGroupID());
                        resp.setAgeGroups(dataUtil.getAgeGroups(gr.getGolfGroupID()));

                        resp.setGolfGroups(new ArrayList<GolfGroupDTO>());
                        resp.getGolfGroups().add(new GolfGroupDTO(gg));

                        break;
                    case RequestDTO.ADD_ADMINISTRATOR:
                        AdministratorDTO admin = dataUtil.addGolfGroupAdmin(gr.getAdministrator());
                        resp.setAdministrators(new ArrayList<AdministratorDTO>());
                        resp.getAdministrators().add(admin);
                        break;
                    case RequestDTO.ADD_GOLF_GROUP:
                        GolfGroupDTO group = dataUtil.addGolfGroup(gr.getGolfGroup());
                        gr.getAdministrator().setGolfGroup(group);
                        AdministratorDTO admin0 = dataUtil.addGolfGroupAdmin(gr.getAdministrator());
                        resp.setGolfGroups(new ArrayList<GolfGroupDTO>());
                        resp.setAdministrators(new ArrayList<AdministratorDTO>());
                        resp.getAdministrators().add(admin0);
                        resp.getGolfGroups().add(group);
                        break;
                    case RequestDTO.UPDATE_GOLF_GROUP:
                        dataUtil.updateGolfGroup(gr.getGolfGroup());
                        break;
                    case RequestDTO.ADD_CLUB:
                        ClubDTO c = dataUtil.addClub(gr.getClub());
                        break;
                    case RequestDTO.GET_CLUBS_IN_COUNTRY:
                        List<ClubDTO> ctryList = dataUtil.getClubsByCountry(gr.getCountryID());
                        resp.setClubs(ctryList);
                        break;
                    case RequestDTO.GET_CLUBS_IN_PROVINCE:
                        List<ClubDTO> pList = dataUtil.getClubsByProvince(gr.getProvinceID());
                        resp.setClubs(pList);
                        break;
                    case RequestDTO.UPDATE_PLAYER_PARENT:
                        //TODO - think!
                        break;
                    case RequestDTO.UPDATE_ADMINISTRATOR:
                        dataUtil.updateAdmin(gr.getAdministrator());
                        break;
                    case RequestDTO.UPDATE_CLUB:
                        dataUtil.updateClub(gr.getClub());
                        break;
                    case RequestDTO.GET_AGE_GROUPS:
                        List<AgeGroupDTO> ageList = dataUtil.getAgeGroups(gr.getGolfGroupID());
                        resp.setAgeGroups(ageList);
                        break;
                    case RequestDTO.GET_AGE_GROUPS_BOYS:
                        List<AgeGroupDTO> ageList1 = dataUtil.getAgeGroupsBoys(gr.getGolfGroupID());
                        resp.setAgeGroups(ageList1);
                        break;
                    case RequestDTO.GET_AGE_GROUPS_GIRLS:
                        List<AgeGroupDTO> ageList2 = dataUtil.getAgeGroupsGirls(gr.getGolfGroupID());
                        resp.setAgeGroups(ageList2);
                        break;
                    case RequestDTO.GET_COUNTRIES:
                        List<CountryDTO> cntrList = dataUtil.getCountries();
                        resp.setCountries(cntrList);
                        break;
                    case RequestDTO.ADD_CLUB_COURSE:
                        dataUtil.addClubCourse(gr.getClubCourse());
                        resp.setClubCourse(gr.getClubCourse());
                        break;
                    case RequestDTO.UPDATE_CLUB_COURSE:
                        dataUtil.updateClubCourse(gr.getClubCourse());
                        break;
                }
           
            } catch (DataException e) {
                resp.setStatusCode(ResponseDTO.DATA_EXCEPTION);
                resp.setMessage("Database failed. Please try again later");
                log.log(Level.SEVERE, "Database failed", e);
            
            } catch (Exception e) {
                resp.setStatusCode(ResponseDTO.GENERIC_EXCEPTION);
                resp.setMessage("Server process failed. Please try again later");
                log.log(Level.SEVERE, "Server failed", e);
            } finally {
                String json = gson.toJson(resp);
                if (gr.isZippedResponse()) {
                    response.setContentType("application/zip;charset=UTF-8");
                    File zipped = null;
                    try {
                        zipped = GZipUtility.getZipped(json);
                        ServletUtils.returnFile(zipped.getAbsolutePath(), response.getOutputStream());
                        response.getOutputStream().close();
                        log.log(Level.OFF, "### Zipped Length of Response: {0} -  "
                                + "unzipped length: {1}", new Object[]{zipped.length(), json.length()});
                    } catch (Exception e) {
                        log.log(Level.SEVERE, "Zipping problem - probably the zipper cannot find the zipped file", e);
                        PrintWriter out = response.getWriter();
                        response.setContentType("application/json;charset=UTF-8");
                        out.println(json);
                        out.close();
                    }
                } else {
                    response.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    out.println(json);
                    out.close();
                }

                long end = System.currentTimeMillis();

                log.log(Level.INFO, "---> MalengaGolf Request completed in {0} seconds", getElapsed(start, end));
            }
        }
    }

    public static double getElapsed(long start, long end) {
        BigDecimal m = new BigDecimal(end - start).divide(new BigDecimal(1000));
        return m.doubleValue();
    }

    private RequestDTO getRequest(Gson gson, HttpServletRequest req) {

        String json = req.getParameter("JSON");
        RequestDTO cr = gson.fromJson(json, RequestDTO.class);

        if (cr == null) {
            cr = new RequestDTO();
        }

        return cr;
    }
    private static final Logger log = Logger.getLogger("GolfAdmin");
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
