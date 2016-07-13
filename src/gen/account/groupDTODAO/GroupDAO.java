/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.account.groupDTODAO;

import gen.ImpExp.TagsHelper1;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.dto.Util;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
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
 * @author admin
 */
public class GroupDAO {
//added Method to upser groups

    public static void upsertGroup(List<gen.account.groupDTODAO.GroupDTO> groupDTOs) throws SQLException, ParseException {
        if (groupDTOs != null && groupDTOs.size() > 0) {
            List<gen.account.groupDTODAO.GroupDTO> groupDTOstoInsert = new ArrayList<gen.account.groupDTODAO.GroupDTO>();
            List<gen.account.groupDTODAO.GroupDTO> groupDTOstoUpdate = new ArrayList<gen.account.groupDTODAO.GroupDTO>();

            for (gen.account.groupDTODAO.GroupDTO groupDTO : groupDTOs) {
                if (groupDTO.getGroup_id().trim().equalsIgnoreCase("")) {
                    groupDTOstoInsert.add(groupDTO);
                } else {
                    groupDTOstoUpdate.add(groupDTO);
                }
            }

            if (groupDTOstoInsert.size() > 0) {
                insertGroup(groupDTOstoInsert);
            }

            if (groupDTOstoUpdate.size() > 0) {
                updateGroup(groupDTOstoUpdate);
            }

        }

    }

    private static void insertGroup(List<gen.account.groupDTODAO.GroupDTO> groupDTOs) throws SQLException, ParseException {
        Connection conn = null;
        try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String q = "insert into tblgroup(group_name,group_alias,group_under,created_date,created_user) values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(q);
            for (gen.account.groupDTODAO.GroupDTO groupDTO : groupDTOs) {
                ps.setString(1, (groupDTO.getGroup_Name().trim()));
                ps.setString(2, groupDTO.getGroup_Alias().trim());
                ps.setString(3, groupDTO.getGroup_Under().trim());


                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabase.parse(groupDTO.getGroup_Date());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(4, sqlStartDate);

                ps.setString(5, groupDTO.getGroup_User());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            e.printStackTrace();
            throw e;
        }
    }

    private static void updateGroup(List<gen.account.groupDTODAO.GroupDTO> groupDTOs) throws SQLException, ParseException {
        Connection conn = null;
        try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            String q = "update tblgroup  set group_name = ?,group_alias =?,group_under=?, modified_date = ?, modified_user = ? where group_id =?";
            PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            for (gen.account.groupDTODAO.GroupDTO groupDTO : groupDTOs) {
                ps.setString(1, (groupDTO.getGroup_Name().trim()));
                ps.setString(2, groupDTO.getGroup_Alias().trim());
                ps.setString(3, groupDTO.getGroup_Under().trim());

                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabase.parse(groupDTO.getGroup_Date());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                ps.setDate(4, sqlStartDate);
                ps.setString(5, groupDTO.getGroup_User());
                ps.setString(6, groupDTO.getGroup_id());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            e.printStackTrace();
            throw e;
        }
    }

    // use for ExportImport class not for groupForm
    public static List<gen.account.groupDTODAO.GroupDTO> getGroupListForExportImport(Set<String> groupIdSet, String type) throws SQLException, Exception {
        List<GroupDTO> groupDTOList = new ArrayList<GroupDTO>();
        System.out.println("TagsHelper----------->>groupMap :" + groupIdSet.size());
        Connection con = null;
        gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
        con = DatabaseConnection1.GetConnection();
        try {
            if (!Util.isEmpty(groupIdSet)) {
                String groupNameList = "";
                if (type.equalsIgnoreCase(Constants.GROUP_NAME)) {
                    for (String group : groupIdSet) {
                        groupNameList = groupNameList + "'" + group + "',";
                    }
                } else if (type.equalsIgnoreCase(Constants.GROUP_ID)) {
                    for (String group : groupIdSet) {
                        groupNameList = groupNameList + "" + group + ",";
                    }
                }


                if (!groupNameList.trim().equalsIgnoreCase("")) {
                    groupNameList = groupNameList.trim().substring(0, groupNameList.length() - 1);
//                    con.setAutoCommit(false);
                    System.out.println("TagsHelper----------->>groupNameList:" + groupNameList);

                    String query = "";
                    if (type.equalsIgnoreCase(Constants.GROUP_NAME)) {
                        query = "select * from tblgroup where group_name in(" + groupNameList + ")";
                    } else if (type.equalsIgnoreCase(Constants.GROUP_ID)) {
                        query = "select * from tblgroup where group_id in(" + groupNameList + ")";
                    }


                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    ResultSet resultSet = preparedStmt.executeQuery();
                    while (resultSet.next()) {
                        GroupDTO groupDTO = new GroupDTO();
                        groupDTO.setGroup_Name(resultSet.getString("group_name"));
                        groupDTO.setGroup_Alias(resultSet.getString("group_alias"));
                        //groupDTO.setGroup_Under(groupIDToNameMapping.get(String.valueOf(resultSet.getInt("group_under"))));

                        groupDTOList.add(groupDTO);
                    }
                }
            }
//	    con.commit();
	    con.close();
        } catch (Exception ex) {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            ex.printStackTrace();
            throw ex;
        }

        return groupDTOList;
    }

    // use for GroupForm class not for ExportImport Class
    public static List<gen.account.groupDTODAO.GroupDTO> getGroupListForGroupForm(Set<String> groupIdSet, String type) throws SQLException, Exception {

        List<gen.account.groupDTODAO.GroupDTO> groupDTOList = new ArrayList<gen.account.groupDTODAO.GroupDTO>();
        String query = "";
        String condition = "";
        Connection con = null;
        gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
        con = DatabaseConnection1.GetConnection();

        try {
            String groupNameList = "";
            if (!Util.isEmpty(groupIdSet)) {
                if (type.equalsIgnoreCase(Constants.GROUP_NAME)) {
                    for (String group : groupIdSet) {
                        groupNameList = group + "," + groupNameList;
                    }
                    String groupNameList1 = groupNameList.substring(0, groupNameList.length() - 1);
                    condition = " where group_name in(" + groupNameList1 + ")";
                } else if (type.equalsIgnoreCase(Constants.GROUP_ID)) {
                    for (String group : groupIdSet) {
                        groupNameList = group + "," + groupNameList;
                    }
                    String groupNameList1 = groupNameList.substring(0, groupNameList.length() - 1);
                    condition = " where group_id in(" + groupNameList1 + ")";
                }
            }

            query = "select * from tblgroup " + condition;
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {

                gen.account.groupDTODAO.GroupDTO groupDTO = new gen.account.groupDTODAO.GroupDTO();
                groupDTO.setGroup_Name(resultSet.getString("group_name"));
                groupDTO.setGroup_Alias(resultSet.getString("group_alias"));
                groupDTO.setGroup_Under(resultSet.getString("group_under"));
                groupDTO.setGroup_id(resultSet.getString("group_id"));
                groupDTOList.add(groupDTO);
            }
	    con.close();
        } catch (Exception ex) {
            if (con != null && !con.isClosed()) {
                con.close();
            }
            ex.printStackTrace();
            throw ex;
        }
        return groupDTOList;
    }

    public static Boolean getLedgerUnderGroupList(Long groupID) throws SQLException, Exception {
        Boolean flag = true;
        Connection conn = null;
        try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            conn = DatabaseConnection1.GetConnection();
            String group_Id = "" + groupID;

            String str = "select * from tblledger where ledger_under =? ";
            PreparedStatement psmt = conn.prepareStatement(str);
            psmt.setString(1, "" + groupID);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                flag = false;
            }
            conn.close();

        } catch (Exception e) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            e.printStackTrace();
            throw e;
        }
        return flag;
    }

    public static void deleteGroup(gen.account.groupDTODAO.GroupDTO groupDTOs) throws SQLException, Exception {
         Connection conn = null;
        try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st1 = conn.createStatement();
            Statement st2 = conn.createStatement();
            Statement st3 = conn.createStatement();
            String query = "";

            String condition = "";

            int counter = 0;
            if (!groupDTOs.equals("null")) {
                condition = groupDTOs.getGroup_id() + "," + condition;

                String groupNameList1 = condition.substring(0, condition.length() - 1);
                st1.executeUpdate("delete from tblgroup where group_id in (" + groupNameList1 + ")");
            }
            conn.commit();
            conn.close();
        } catch (Exception e) {
             if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            e.printStackTrace();
            throw e;
        }
    }

    public static Map<String, String> getGroupName(List<String> groupNames, Boolean isIn) throws SQLException, Exception {
        Map<String, String> mapToReturn = new HashMap<String, String>();
        String mainQuery = "";
        String condition = "";
        Connection connection = null;
        gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
        connection = DatabaseConnection1.GetConnection();
        try {
            
        
        mainQuery = " select group_id,group_Name from tblgroup";
        if (groupNames != null && groupNames.size() > 0) {
            String groupList = "";
            Set<String> groupSet = new HashSet<String>();
            for (String stkGroup : groupNames) {
                groupSet.add(stkGroup);
            }

            groupSet = GroupDAO.getUnderGroups(groupSet);
            groupNames = new ArrayList<String>();
            for (String stkGroup : groupSet) {
                groupNames.add(stkGroup);
            }

            for (String stkGroup : groupNames) {
                groupList += "'" + stkGroup + "' ,";
            }
            if (groupList.length() > 0) {
                groupList = groupList.substring(0, groupList.length() - 2);
            }

            if (isIn == null || isIn) {
                condition = " WHERE      tblgroup.group_name IN  (" + groupList + ")";
            } else {
                condition = " WHERE      tblgroup.group_name NOT IN  (" + groupList + ")";
            }

        }
        String query = mainQuery + condition;
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();
        while (rsSet.next()) {
            mapToReturn.put(rsSet.getString("group_name"), rsSet.getLong("group_id") + "");
        }
        rsSet.close();
        psmt.close();
        connection.close();
        } catch (Exception ex) {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            ex.printStackTrace();
            throw ex;
        }
        return mapToReturn;
    }

    public static Map<String, String> getGroupNameWithUnder(List<String> groupNames, Boolean isIn) throws SQLException, Exception {
	Map<String, String> mapToReturn = new HashMap<String, String>();
	String mainQuery = "";
	String condition = "";
	Connection connection = null;
	try {
	    mainQuery = " select group_id,group_under from tblgroup";
	    if (groupNames != null && groupNames.size() > 0) {
		String groupList = "";
		Set<String> groupSet = new HashSet<String>();
		for (String stkGroup : groupNames) {
		    groupSet.add(stkGroup);
		}

            groupSet = GroupDAO.getUnderGroups(groupSet);
		groupNames = new ArrayList<String>();
		for (String stkGroup : groupSet) {
		    groupNames.add(stkGroup);
		}

		for (String stkGroup : groupNames) {
		    groupList += "'" + stkGroup + "' ,";
		}
		if (groupList.length() > 0) {
		    groupList = groupList.substring(0, groupList.length() - 2);
		}

		if (isIn == null || isIn) {
		    condition = " WHERE      tblgroup.group_name IN  (" + groupList + ")";
		} else {
		    condition = " WHERE      tblgroup.group_name NOT IN  (" + groupList + ")";
		}

	    }

	    String query = mainQuery + condition;
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
	    connection = DatabaseConnection1.GetConnection();
	    PreparedStatement psmt = connection.prepareStatement(query);
	    ResultSet rsSet = psmt.executeQuery();
	    while (rsSet.next()) {
		mapToReturn.put(rsSet.getString("group_id"), rsSet.getString("group_under") + "");
	    }
	    rsSet.close();
	    psmt.close();
	    connection.close();
	} catch (Exception ex) {
	    if (connection != null && !connection.isClosed()) {
		connection.close();
	    }
	    ex.printStackTrace();
	    throw ex;
	}
	return mapToReturn;
    }

    // use for Heirarchy 
    public static List<String> getGroupIDListForGroupHeirarchy(String group_id) throws SQLException, Exception {

	List<String> check_Group_ID_List = new ArrayList<String>();
	String query = "";
	Connection con = null;
	check_Group_ID_List.add(group_id);
	try {
	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
	    con = DatabaseConnection1.GetConnection();
	    for (int i = 0; i < check_Group_ID_List.size(); i++) {
		query = "select group_id from tblgroup where group_under = " + check_Group_ID_List.get(i);
		PreparedStatement preparedStmt1 = con.prepareStatement(query);
		ResultSet resultSet1 = preparedStmt1.executeQuery();
		while (resultSet1.next()) {
		    check_Group_ID_List.add(resultSet1.getString("group_id"));
		}
	    }
	    check_Group_ID_List.remove(group_id);
	    con.close();
	} catch (Exception ex) {
	    if (con != null && !con.isClosed()) {
		con.close();
	    }
	    ex.printStackTrace();
	    throw ex;
	}
	return check_Group_ID_List;
    }
    
     public static synchronized Set<String> getUnderGroups(Set<String> groupNames) {
        List<gen.dto.GroupDTO> groupDTOList = new ArrayList<gen.dto.GroupDTO>();
        groupDTOList = getGroups(null);
        Map<String, String> groupNameUnderMap = new HashMap<String, String>();
        Map<String, String> groupNameIDMap = new HashMap<String, String>();
        Map<String, Set<String>> groupUnderNameListMap = new HashMap<String, Set<String>>();
        for (gen.dto.GroupDTO groupDTO : groupDTOList) {
            groupNameUnderMap.put(groupDTO.getGroup_Name(), groupDTO.getGroup_Under());
            groupNameIDMap.put(groupDTO.getGroup_Name(), groupDTO.getGroup_id());
            if (groupUnderNameListMap.get(groupDTO.getGroup_Under()) == null) {
                groupUnderNameListMap.put(groupDTO.getGroup_Under(), new HashSet<String>());
            }
            groupUnderNameListMap.get(groupDTO.getGroup_Under()).add(groupDTO.getGroup_Name());
        }
        Boolean flag = true;
        while (flag) {
            System.out.println("In the under--------->>>>flag : " + flag);
            int size = groupNames.size();
            Set<String> nameSet = new HashSet<String>();
            for (String groupName : groupNames) {
                if (groupUnderNameListMap.get(groupNameIDMap.get(groupName)) != null) {
                    nameSet.addAll(groupUnderNameListMap.get(groupNameIDMap.get(groupName)));
                }
            }
            if (nameSet.size() > 0) {
                groupNames.addAll(nameSet);
            }
            if (size == groupNames.size()) {
                flag = false;
            }
        }
        return groupNames;
    }

    public static synchronized List<gen.dto.GroupDTO> getGroups(Set<String> groupSet) {
        List<gen.dto.GroupDTO> groupDTOList = new ArrayList<gen.dto.GroupDTO>();
        String condition = "";
        String groupNameList = "";

        if (groupSet != null && groupSet.size() > 0) {

            for (String group : groupSet) {
                groupNameList += groupNameList + "'" + group + "',";
            }
            if (!groupNameList.trim().equalsIgnoreCase("")) {
                groupNameList = groupNameList.trim().substring(0, groupNameList.length() - 1);
            }
            condition = " where group_name in(" + groupNameList + ")";
        }
        Connection con = DatabaseConnection1.GetConnection();
        String query = "";
        query = "select * from tblgroup " + condition;
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                gen.dto.GroupDTO groupDTO = new gen.dto.GroupDTO();
                groupDTO.setGroup_id(resultSet.getString("group_id"));
                groupDTO.setGroup_Name(resultSet.getString("group_name"));
                groupDTO.setGroup_Alias(resultSet.getString("group_alias"));
                groupDTO.setGroup_Under(resultSet.getInt("group_under") + "");
                groupDTOList.add(groupDTO);
            }
	    con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groupDTOList;
    }

    public static Map<String, String> getGroups(List<String> groupNames, Boolean isIn) throws SQLException {
        Map<String, String> mapToReturn = new HashMap<String, String>();
        String mainQuery = "";
        String condition = "";
        mainQuery = "SELECT     tblgroup.group_name AS tblgroup_group_name, tblgroup.group_id as tblgroup_group_id FROM      tblgroup as tblgroup  ";
        if (groupNames != null && groupNames.size() > 0) {
            String groupList = "";
            for (String stkGroup : groupNames) {
                groupList += "'" + stkGroup + "' ,";
            }
            if (groupList.length() > 0) {
                groupList = groupList.substring(0, groupList.length() - 2);
            }

            if (isIn == null || isIn) {
                condition = " WHERE      tblgroup.group_name IN  (" + groupList + ")";
            } else {
                condition = " WHERE      tblgroup.group_name NOT IN  (" + groupList + ")";
            }

        }
        String query = mainQuery + condition;

        System.out.println("----------------->> query: " + query);
        Connection connection = DatabaseConnection1.GetConnection();
        PreparedStatement psmt = connection.prepareStatement(query);
        ResultSet rsSet = psmt.executeQuery();
        while (rsSet.next()) {
            System.out.println("----------------->> in while ");
            mapToReturn.put(rsSet.getString("tblgroup_group_name"), rsSet.getLong("tblgroup_group_id") + "");
        }
        rsSet.close();
        psmt.close();
        connection.close();
        return mapToReturn;
    }
    
    public static List<gen.account.groupDTODAO.GroupDTO> exportAllGroups() {

        List<gen.account.groupDTODAO.GroupDTO> groupDTOList = new ArrayList<gen.account.groupDTODAO.GroupDTO>();
        Connection con = DatabaseConnection1.GetConnection();
        String query = "";
        query = "select * from tblgroup where group_name != 'Primary'";
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                gen.account.groupDTODAO.GroupDTO groupDTO = new gen.account.groupDTODAO.GroupDTO();
                groupDTO.setGroup_id(resultSet.getString("group_id"));
                groupDTO.setGroup_Name(resultSet.getString("group_name"));
                groupDTO.setGroup_Alias(resultSet.getString("group_alias"));
                groupDTO.setGroup_Under(resultSet.getInt("group_under") + "");
                groupDTOList.add(groupDTO);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TagsHelper1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groupDTOList;
    }
}
