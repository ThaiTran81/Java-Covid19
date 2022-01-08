/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utils;

import com.Controller.CovidDAO;
import com.Model.profileModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import java.util.List;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThaiTran
 */
public class FUtil {

     public static DefaultDirectedGraph<String, DefaultEdge> directedGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
     static List<profileModel> lst;

    public static void buildGraph() throws SQLServerException, SQLException {
         
        lst = new CovidDAO().getAllUser();
        for (profileModel userCovid : lst) {
            String id = userCovid.getUsername();
            directedGraph.addVertex(id);
            String idRelated = userCovid.getRelated_id();
            if (idRelated != null) {
                if (!directedGraph.containsVertex(idRelated)) {
                    directedGraph.addVertex(idRelated);
                }
                directedGraph.addEdge(idRelated, id);
            }
        }

    }

    public static String changeState(int state) {
        return state == 1 ? "F1" : state == 2 ? "F2" : state == 3 ? "F3" : state == 4 ? "F4" : "F0";
    }

    public static boolean updateUserCovidByState(String id, String state, String currentState) {
        Object[] params = {
            id,
            state
        };
        if (state.equals("OK")) {
            try {
                new CovidDAO().updateState(params);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        if (currentState.equals("F3")) {
            profileModel currentUser;
            try {
                currentUser = new CovidDAO().getProfileUser(id);
                params[0] = currentUser.getRelated_id();
                params[1] = "F1";
                new CovidDAO().updateState(params);

                params[0] = id;
                params[1] = "F0";
                new CovidDAO().updateState(params);
                traversalAndUpdate(currentUser.getRelated_id(), 2, id);
                traversalAndUpdate(id, 1, id);

            } catch (SQLServerException ex) {
                Logger.getLogger(FUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FUtil.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            params[0] = id;
            params[1] = "F0";

            try {
                 new CovidDAO().updateState(params);
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
            traversalAndUpdate(id, 1, "");

        }

        return true;
    }

    static void traversalAndUpdate(String first, int count, String id) {

        // iterater through the first layer node using breadth first search
        Queue<String> queue = new LinkedList<>();
        queue.add(first);

        int layer = count;
        while (!queue.isEmpty()) {
            String node = queue.poll();

            // get al the vertex that is connected to the current node
            Set<DefaultEdge> set = directedGraph.outgoingEdgesOf(node);
            for (DefaultEdge edge : set) {

                String next = directedGraph.getEdgeTarget(edge);
                if ((next.equals(id))) {
                    continue;
                }
                String newState = changeState(count);
                queue.add(next);
                Object[] param = {
                    next,
                    newState
                };
                try {
                    new CovidDAO().updateState(param);
                } catch (Exception e) {
                    System.out.println("Update failed");
                }
            }
            count++;
        }
    }

}
