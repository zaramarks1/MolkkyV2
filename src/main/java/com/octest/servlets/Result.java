package com.octest.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octest.beans.Game;
import com.octest.beans.Round;
import com.octest.beans.Team;
import com.octest.dao.DaoFactory;

@WebServlet("/Result")
public class Result extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public Result() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
		
		Team team1 = (Team) session.getAttribute("team1");
	    Team team2 = (Team) session.getAttribute("team2");
	    
		Round round = (Round) session.getAttribute("round");    	
		
		Game game = round.getGame();
	    
	    DaoFactory dao = DaoFactory.getInstance();
	    
	    
	    int nbRoundTotal = round.getNbRound();
	    
	    List<Round> roundsT1 = dao.getRoundDao().getByTeamAndGame(team1, game);
	    List<Round> roundsT2 = dao.getRoundDao().getByTeamAndGame(team2, game);
	    	    
	    List<Game>  gamesT1 = dao.getGameDao().getGamesByTeamId(team1.getId());
	    List<Game>  gamesT2 = dao.getGameDao().getGamesByTeamId(team2.getId());

        session.setAttribute("roundsT1", roundsT1);
        session.setAttribute("roundsT2", roundsT2);
        session.setAttribute("gamesT1", gamesT1);
        session.setAttribute("gamesT2", gamesT2);
	    
        
    	if( request.getParameter("Back Home") != null) {  
			this.getServletContext().getRequestDispatcher("/WEB-INF/teams.jsp").forward(request, response);
    	}
    	if( request.getParameter("Historical")!= null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/historical.jsp").forward(request, response);
    	}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);
    	
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
 
}
