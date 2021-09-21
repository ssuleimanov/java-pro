package homework.lesson_2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "voteServlet", value = "/vote")
public class VoteServlet extends HttpServlet {


    static Map<String, Integer> questions = new HashMap<>();

    static {
        questions.put("kyivTrue", 0);
        questions.put("kyivFalse", 0);
        questions.put("dynamoTrue", 0);
        questions.put("dynamoFalse", 0);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        String firstQuestion = request.getParameter("city");
        String secondQuestion = request.getParameter("footballClub");

        for (Map.Entry<String, Integer> entry : questions.entrySet()) {
            if (firstQuestion.equals(entry.getKey())) {
                int i = entry.getValue();
                entry.setValue(i + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : questions.entrySet()) {
            if (secondQuestion.equals(entry.getKey())) {
                int i = entry.getValue();
                entry.setValue(i + 1);
            }
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.println("<html><head><style>table {font-family: arial, sans-serif;border-collapse: collapse;width: 100%;}td, th {border: 1px solid #dddddd;text-align: left;padding: 8px;}tr:nth-child(even) {background-color: #dddddd;}</style></head>");
        printWriter.println("<body><h2>Questionnaire results</h2><table><tr><th>Question</th><th>Yes</th><th>No</th></tr><tr><td>Question about city</td><td>" + questions.get("kyivTrue") + "</td><td>" + questions.get("kyivFalse") + "</td></tr><tr><td>Question about football club</td><td>" + questions.get("dynamoTrue") + "</td><td>" + questions.get("dynamoFalse") + "</td></tr></table></body></html>");
    }

    public void destroy() {
    }
}