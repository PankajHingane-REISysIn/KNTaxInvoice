/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.database.connection;

import gen.ImpExp.TagsHelper1;
import gen.dto.GroupDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc5
 */
public class GroupDAO {

//    public static synchronized Set<String> getUnderGroups(Set<String> groupNames) {
//        List<GroupDTO> groupDTOList = new ArrayList<GroupDTO>();
//        groupDTOList = getGroups(null);
//        Map<String, String> groupNameUnderMap = new HashMap<String, String>();
//        Map<String, String> groupNameIDMap = new HashMap<String, String>();
//        Map<String, Set<String>> groupUnderNameListMap = new HashMap<String, Set<String>>();
//        for (GroupDTO groupDTO : groupDTOList) {
//            groupNameUnderMap.put(groupDTO.getGroup_Name(), groupDTO.getGroup_Under());
//            groupNameIDMap.put(groupDTO.getGroup_Name(), groupDTO.getGroup_id());
//            if (groupUnderNameListMap.get(groupDTO.getGroup_Under()) == null) {
//                groupUnderNameListMap.put(groupDTO.getGroup_Under(), new HashSet<String>());
//            }
//            groupUnderNameListMap.get(groupDTO.getGroup_Under()).add(groupDTO.getGroup_Name());
//        }
//        Boolean flag = true;
//        while (flag) {
//            System.out.println("In the under--------->>>>flag : " + flag);
//            int size = groupNames.size();
//            Set<String> nameSet = new HashSet<String>();
//            for (String groupName : groupNames) {
//                if (groupUnderNameListMap.get(groupNameIDMap.get(groupName)) != null) {
//                    nameSet.addAll(groupUnderNameListMap.get(groupNameIDMap.get(groupName)));
//                }
//            }
//            if (nameSet.size() > 0) {
//                groupNames.addAll(nameSet);
//            }
//            if (size == groupNames.size()) {
//                flag = false;
//            }
//        }
//        return groupNames;
//    }
//
//    public static synchronized List<GroupDTO> getGroups(Set<String> groupSet) {
//        List<GroupDTO> groupDTOList = new ArrayList<GroupDTO>();
//        String condition = "";
//        String groupNameList = "";
//
//        if (groupSet != null && groupSet.size() > 0) {
//
//            for (String group : groupSet) {
//                groupNameList += groupNameList + "'" + group + "',";
//            }
//            if (!groupNameList.trim().equalsIgnoreCase("")) {
//                groupNameList = groupNameList.trim().substring(0, groupNameList.length() - 1);
//            }
//            condition = " where group_name in(" + groupNameList + ")";
//        }
//        Connection con = DatabaseConnection1.GetConnection();
//        String query = "";
//        query = "select * from tblgroup " + condition;
//        try {
//            PreparedStatement preparedStmt = con.prepareStatement(query);
//            ResultSet resultSet = preparedStmt.executeQuery();
//            while (resultSet.next()) {
//                GroupDTO groupDTO = new GroupDTO();
//                groupDTO.setGroup_id(resultSet.getString("group_id"));
//                groupDTO.setGroup_Name(resultSet.getString("group_name"));
//                groupDTO.setGroup_Alias(resultSet.getString("group_alias"));
//                groupDTO.setGroup_Under(resultSet.getInt("group_under") + "");
//                groupDTOList.add(groupDTO);
//            }
//	    con.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return groupDTOList;
//    }
//
//    public static Map<String, String> getGroups(List<String> groupNames, Boolean isIn) throws SQLException {
//        Map<String, String> mapToReturn = new HashMap<String, String>();
//        String mainQuery = "";
//        String condition = "";
//        mainQuery = "SELECT     tblgroup.group_name AS tblgroup_group_name, tblgroup.group_id as tblgroup_group_id FROM      tblgroup as tblgroup  ";
//        if (groupNames != null && groupNames.size() > 0) {
//            String groupList = "";
//            for (String stkGroup : groupNames) {
//                groupList += "'" + stkGroup + "' ,";
//            }
//            if (groupList.length() > 0) {
//                groupList = groupList.substring(0, groupList.length() - 2);
//            }
//
//            if (isIn == null || isIn) {
//                condition = " WHERE      tblgroup.group_name IN  (" + groupList + ")";
//            } else {
//                condition = " WHERE      tblgroup.group_name NOT IN  (" + groupList + ")";
//            }
//
//        }
//        String query = mainQuery + condition;
//
//        System.out.println("----------------->> query: " + query);
//        Connection connection = DatabaseConnection1.GetConnection();
//        PreparedStatement psmt = connection.prepareStatement(query);
//        ResultSet rsSet = psmt.executeQuery();
//        while (rsSet.next()) {
//            System.out.println("----------------->> in while ");
//            mapToReturn.put(rsSet.getString("tblgroup_group_name"), rsSet.getLong("tblgroup_group_id") + "");
//        }
//        rsSet.close();
//        psmt.close();
//        connection.close();
//        return mapToReturn;
//    }
}
