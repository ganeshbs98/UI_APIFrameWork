package com.database.dao;

import com.api.database.DataBaseManager;
import com.database.model.MapJobProblemModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapJobProblemDao {
    private static final Logger logger= LogManager.getLogger(MapJobProblemDao.class);

    private static final String PROBLEM_QUERY = """
            select * from map_job_problem  WHERE tr_job_head_id =?
            """;

    private MapJobProblemDao() {
    }
    public static MapJobProblemModel getProblemsDetails(int tr_JobHead_id) {
        Connection conn;
        PreparedStatement pState;
        ResultSet rSet;
        MapJobProblemModel mapJobProblemModel = null;
        try {
            conn = DataBaseManager.getConnection();
            pState = conn.prepareStatement(PROBLEM_QUERY);
            pState.setInt(1, tr_JobHead_id);
            logger.info("Executing query to fetch problem details for Job Head ID: " + tr_JobHead_id);
            rSet = pState.executeQuery();
            logger.info("Processing result set for problem details");
            while (rSet.next()) {
                mapJobProblemModel = new MapJobProblemModel(
                        rSet.getInt("id"),
                        rSet.getInt("tr_job_head_id"),
                        rSet.getInt("mst_problem_id"),
                        rSet.getString("remark")
                );
                System.out.println(mapJobProblemModel);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return mapJobProblemModel;

    }
}
