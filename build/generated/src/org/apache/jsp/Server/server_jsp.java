package org.apache.jsp.Server;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;
import Utility.ProductPojo;
import java.util.Vector;
import java.util.Iterator;
import dbConnection.DbConnection;

public final class server_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    DbConnection conn = new DbConnection();
    String key = request.getParameter("key");
    System.out.println(key);

    if (key.equals("reg_user")) {
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String aadhar = request.getParameter("aadhar");
        String password = request.getParameter("password");
        System.out.println("name " + name + " phone " + phone + " email " + email);

        String str = "select count(*) from login where  username = '" + phone + "'";
        System.out.println(str);
        Iterator it = conn.getData(str).iterator();
        if (it.hasNext()) {
            Vector v = (Vector) it.next();
            int max_vid = Integer.parseInt(v.get(0).toString());
            System.out.println(max_vid);
            if (max_vid == 0) {

                String qry = "INSERT INTO `user_registration`(`name`,`gender`,`aadhaar`,`phone`,`email`) VALUES ('" + name + "','" + gender + "','" + aadhar + "','" + phone + "','" + email + "') ";
                String qry1 = "insert into login (`uid`,`username`,`password`,`type`,`status`)values((select max(uid)from user_registration),'" + phone + "','" + password + "','user',1)";
                if (conn.putData(qry) > 0) {

                    if (conn.putData(qry1) > 0) {
                        out.print("successful");
                    }

                }
                
            } else {
                out.print("failed");
            }

        } else {
            out.print("failed");
        }
    }
    
//----------------------------------LOGIN-------------------------------------//
    if (key.equals("login")) {
        String info = "";
        
        String qry = "select * from `login` where username='" + request.getParameter("username") + "' and password='" + request.getParameter("password") + "' ";
        System.out.println("qry=" + qry);

        Iterator it = conn.getData(qry).iterator();
        if (it.hasNext()) {
            Vector v = (Vector) it.next();
            
            
            if(v.get(4).equals("admin")){
                System.out.println("successfull");
                
                 out.print("successfull");
            }
            else if(v.get(4).equals("user")) {
                String qry1 = "select name from `user_registration` where uid = '"+v.get(1)+"' ";
        System.out.println("qry=" + qry1);

        Iterator it1 = conn.getData(qry1).iterator();
        if (it1.hasNext()) {
            Vector v1 = (Vector) it1.next();
            
                
                info = v.get(1) + ":" + v.get(2) + ":" + v.get(4)+":"+v1.get(0);
            System.out.println(info);
            out.print(info);
            }
            } 
            
            else if(v.get(4).equals("restaurant")) {
                String qry1 = "select name from `restaurant_register` where rid = '"+v.get(1)+"' ";
        System.out.println("qry=" + qry1);

        Iterator it1 = conn.getData(qry1).iterator();
        if (it1.hasNext()) {
            Vector v1 = (Vector) it1.next();
            
                
                info = v.get(1) + ":" + v.get(2) + ":" + v.get(4)+":"+v1.get(0);
            System.out.println(info);
            out.print(info);
            }
            } 
            
            else if(v.get(4).equals("delivery_boy")) {
                String qry1 = "select name from `delivery_boy` where did = '"+v.get(1)+"' ";
        System.out.println("qry=" + qry1);

        Iterator it1 = conn.getData(qry1).iterator();
        if (it1.hasNext()) {
            Vector v1 = (Vector) it1.next();
            
                
                info = v.get(1) + ":" + v.get(2) + ":" + v.get(4)+":"+v1.get(0);
            System.out.println(info);
            out.print(info);
            }
            } 
            
            
            
        } else {
            System.out.println("else id=" + info);
            out.print("failed");
        }
    }


//---------------------------------Restaurennt Registration--------------------//
if (key.equals("reg_restaurant")) {
        String name = request.getParameter("name");
        String latitude = request.getParameter("latitude");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String longitude = request.getParameter("longitude");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String district = request.getParameter("district");
        String ownername = request.getParameter("ownername");
        String stationid = request.getParameter("stationid");
        
        System.out.println("name " + name + " phone " + phone + " email " + email);

        String str = "select count(*) from login where  username = '" + phone + "'";
        System.out.println(str);
        Iterator it = conn.getData(str).iterator();
        if (it.hasNext()) {
            Vector v = (Vector) it.next();
            int max_vid = Integer.parseInt(v.get(0).toString());
            System.out.println(max_vid);
            if (max_vid == 0) {

                String qry = "INSERT INTO `restaurant_register`(`station_id`,`name`,`owner`,`location`,`district`,`phone`,`email`,`latt`,`longg`) VALUES ('" + stationid + "','" + name + "','" + ownername + "','" + address + "','" + district + "','" + phone + "','" + email + "','" + latitude + "','" + longitude + "') ";
                String qry1 = "insert into login (`uid`,`username`,`password`,`type`,`status`)values((select max(rid)from restaurant_register),'" + phone + "','" + password + "','restaurant',0)";
                if (conn.putData(qry) > 0 && conn.putData(qry1) > 0) {

                   
                         System.out.print("successful");
                        out.print("successful");
              

                }
                
            } else {
                out.print("failed");
            }

        } else {
            out.print("failed");
        }
    }
    

//----------------------district---------------------------------------
if(key.equals("district")){
    
    String str="select * from district";
    System.out.println(str);
    Iterator it = conn.getData(str).iterator();
    String dist="";
    if(it.hasNext()){
       
       while(it.hasNext()){
            Vector v = (Vector) it.next();
            dist += v.get(1)+":";
       }
       
       
       out.println(dist);
       System.out.println(dist);

        } else {
            out.println("failed");
        }
}


//-----------------------------------Add Stations-----------------------------//

if(key.equals("add_station")){
    
    String latitude = request.getParameter("latitude");
    String longitude = request.getParameter("longitude");
    String name = request.getParameter("name");
    System.out.println(name);
    String address = request.getParameter("address");
    String district = request.getParameter("district");

    String val="";
    
    
      String str = "select count(*) from station where  name = '" + name + "'";
        System.out.println(str);
        Iterator it = conn.getData(str).iterator();
        if (it.hasNext()) {
            Vector v = (Vector) it.next();
            int max_vid = Integer.parseInt(v.get(0).toString());
            System.out.println(max_vid);
            if (max_vid == 0) {

    String str1="insert into station(`name`,`district`,`address`,`latt`,`longg`) values ('"+name+"','"+district+"','"+address+"','"+latitude+"','"+longitude+"')";
    System.out.println(str1);
    if(conn.putData(str1)>0){
        out.println("Success");
        
    }
    else{
       out.println("failed");
    }
    System.out.println(val);
        }else{
                
                 System.out.println("Already Existing");
       out.println("Already Existing");
    }

            
        }
        else{
       out.println("failed");
    }

}  




//--------------------------stations_list---------------------------------------//
if(key.equals("stations_list")){
    
    String str="select * from station";
    System.out.println(str);
    Iterator it = conn.getData(str).iterator();
    String district="",id="",lat="",longg="",name="",address="",result="";
    if(it.hasNext()){
       
       while(it.hasNext()){
            Vector v = (Vector) it.next();
            district += v.get(2)+":";
            id += v.get(0)+":";
            name += v.get(1)+":";
            address += v.get(3)+":";
            lat += v.get(4)+":";
            longg += v.get(5)+":";
            result += "\nNAME              : "+v.get(1)+"\nDISTRICT        : "+v.get(2)+"\nADDRESS        : "+v.get(3)+"\nLATITUDE        : "+v.get(4)+"\nLONGITUDE     : "+v.get(5)+"///";
            
            
       }
       
       
       out.println(id+"#"+name+"#"+district+"#"+address+"#"+lat+"#"+longg+"#"+result);
       System.out.println(id+"#"+name+"#"+district+"#"+address+"#"+lat+"#"+longg);

        } else {
            out.println("failed");
        }
}

//----------------------------------get detail----------------------------------
if(key.equals("getdetail")){
    String u_id = request.getParameter("u_id");
  
    String type = request.getParameter("type");
    System.out.println(u_id);
    if(type.matches("user")){
         String str="select u.*,l.password from user_registration u, login l  where u.uid = '"+u_id+"' and u.uid = l.uid and type = 'user'";
    System.out.println(str);
    Iterator it = conn.getData(str).iterator();
    String dist="",value ="",result="",ph="";
    if(it.hasNext()){
       
       while(it.hasNext()){
            Vector v = (Vector) it.next();
            dist += v.get(0)+"#"+v.get(1)+"#"+v.get(3)+"#"+v.get(4)+"#"+v.get(5)+"#"+v.get(6);
          }
       out.println(dist);
       System.out.println(dist);

        } else {
            out.println("failed");
        }
    }
    if (type.matches("restaurant")){
        String str="select r.*,l.password from restaurant_register r, login l  where r.rid = '"+u_id+"' and r.rid = l.uid and type = 'restaurant'";
    System.out.println(str);
    Iterator it = conn.getData(str).iterator();
    String dist="",value ="",result="",ph="";
    if(it.hasNext()){
       
       while(it.hasNext()){
            Vector v = (Vector) it.next();
            result += v.get(2)+"#"+v.get(3)+"#"+v.get(6)+"#"+v.get(7)+"#"+v.get(10);
          }
       out.println(result);
       System.out.println(result);

        } else {
            out.println("failed");
        }
    }
    if (type.matches("delivery_boy")){
        String str="select d.*,l.password from delivery_boy d, login l  where d.did = '"+u_id+"' and d.did = l.uid and type = 'delivery_boy'";
    System.out.println(str);
    Iterator it = conn.getData(str).iterator();
    String dist="",value ="",result="",ph="";
    if(it.hasNext()){
       
       while(it.hasNext()){
            Vector v = (Vector) it.next();
            result += v.get(3)+"#"+v.get(5)+"#"+v.get(7)+"#"+v.get(8)+"#"+v.get(11);
          }
       out.println(result);
       System.out.println(result);

        } else {
            out.println("failed");
        }
    }
    
}

//-----------------------------------------------
if(key.equals("update")){
    String u_id = request.getParameter("u_id");
    
    String NAME = request.getParameter("NAME");
    String PHONE = request.getParameter("PHONE");
   
    String EMAIL = request.getParameter("EMAIL");
    String type = request.getParameter("TYPE");
        String PASS = request.getParameter("PASS");
    System.out.println(type);
    if(type.matches("user")){
         String IDPROOF = request.getParameter("IDPROOF");
         String str="update user_registration set name='"+NAME+"', phone='"+PHONE+"', aadhaar='"+IDPROOF+"', email='"+EMAIL+"' where uid = '"+u_id+"'";
                  String str1="update login set username='"+PHONE+"', password='"+PASS+"' where uid = '"+u_id+"' and type = 'user'";

    System.out.println(str);
    if (conn.putData(str) > 0 && conn.putData(str1) > 0 ) {

            out.print("successful");
        }
    else {
            out.println("failed");
        }
    }
    if(type.matches("delivery_boy")){
         String IDPROOF = request.getParameter("IDPROOF");
         String str="update delivery_boy set name='"+NAME+"', phone='"+PHONE+"', aadhar='"+IDPROOF+"', email='"+EMAIL+"' where did = '"+u_id+"'";
                  String str1="update login set username='"+PHONE+"', password='"+PASS+"' where uid = '"+u_id+"' and type = 'delivery_boy'";

    System.out.println(str);
    if (conn.putData(str) > 0 && conn.putData(str1) > 0 ) {

            out.print("successful");
        }
    else {
            out.println("failed");
        }
    }
     if(type.matches("restaurant")){
         
         String OWNERNAME = request.getParameter("OWNERNAME");
         String str="update restaurant_register set name='"+NAME+"',owner='"+OWNERNAME+"',phone='"+PHONE+"', email='"+EMAIL+"' where rid = '"+u_id+"'";
                  String str1="update login set username='"+PHONE+"', password='"+PASS+"' where uid = '"+u_id+"' and type = 'restaurant'";

    System.out.println(str+" "+str1);
    
    String dist="",value ="",result="",ph="";
    if (conn.putData(str) > 0 && conn.putData(str1) > 0 ) {

            out.print("successful");
        }
    else {
            out.println("failed");
        }
    }
    
}

  ///////////////////////////////////////////////////////////////////////////////////////////////

    if (key.equals("getACcountDetails")) {
        String result = "";
        String uid = request.getParameter("uid").toString();
        String qry = "SELECT * FROM `bank` WHERE `u_id`='" + uid + "'";
        System.out.println("qry  " + qry);
        Iterator itr = conn.getData(qry).iterator();
        if (itr.hasNext()) {
            Vector v = (Vector) itr.next();

            result = v.get(2) + "#" + v.get(3) + "#" + v.get(4) + "#" + v.get(5)+ "#" + v.get(6);
            out.println(result);
        } else {
            out.println("failed");
        }

    }

///////////////////////////////////////////////////////////////////////////////////////////////
if (key.equals("newaccount")) {

        String result = "";
        System.out.println("Haiiiiiiiiiiiiii");
        String uid = request.getParameter("uid").toString();

        System.out.println(uid);
        String pin = request.getParameter("pin").toString();
        System.out.println(pin);

        String accno = request.getParameter("cardnum").toString();
        String cvv = request.getParameter("cvv").toString();
        String balance = request.getParameter("balance").toString();
        String name = request.getParameter("name").toString();

        System.out.println(uid + " " + pin + " " + accno + " " + cvv + " " + balance);

        String checkqry = "SELECT * FROM `bank` WHERE `u_id`='" + uid + "'";

        String str = " INSERT INTO `bank` (`u_id`,`bname`,`card_no`,`cvv_no`,`pin`,`balance`,`status`) "
                + "VALUES ('" + uid + "','" + name + "','" + accno + "','" + cvv + "','" + pin + "','" + balance + "','1')";

        System.out.println(str);

        Iterator itr = conn.getData(checkqry).iterator();
        if (itr.hasNext()) {

            System.out.println(checkqry);
            out.println("accountexists");

            String str3 = "UPDATE `bank` SET `bname`='" + name + "',`balance`='" + balance + "', `card_no`='" + accno + "' , `cvv_no`='" + cvv + "' , `pin`='" + pin + "' WHERE u_id='" + uid + "'";
            if (conn.putData(str3) > 0) {
                out.println("success");
            } else {
                out.println("failed");
            }

        } else {
            if (conn.putData(str) > 0) {

                out.print("success");
                System.out.println("success");
            } else {

                out.println("failed");
            }
        }
    }



////////////////////////////////////////////////////////////////////////////////
    if (key.equals("addfeedback")) {
        String sub = request.getParameter("subject");
        String feedback = request.getParameter("feedback");
        String uid = request.getParameter("u_id");
        String date = java.time.LocalDate.now().toString();

        String qry = "INSERT INTO `feedback` (`u_id`,`subject`,`description`,`posted_date`) VALUES ('" + uid + "','" + sub + "','" + feedback + "','"+date+"') ";
        System.out.println(qry);
        if (conn.putData(qry) > 0) {

            out.print("successful");
        } else {
            out.print("failed");
        }
    }


/////////////////////////////////////////////////////////////////////////////////

//   getComplaintReply
    if (key.equals("getfeedback")) {
        String result = "";
//        String uid = request.getParameter("uid").toString();
        String qry = "SELECT  c.`subject`,c.`description`,u.`name` FROM `feedback` c,`user_registration` u WHERE c.`u_id`=u.`uid` ";
        System.out.println("qry  " + qry);
        Iterator itr = conn.getData(qry).iterator();
        if (itr.hasNext()) {
            while (itr.hasNext()) {
                Vector v = (Vector) itr.next();

                result += "\nUSER NAME        :  " + v.get(2) +"\nSUBJECT             :  " + v.get(0) + "\nFEEDBACK           :  " + v.get(1) +"\n"+  "#";

            }
            out.println(result);

        } else {
            out.println("failed");
        }

    }
    


////////////////////////////////////////////////////////////////////////////////



if(key.equals("near_by_stations")){
    
    String district = request.getParameter("district");
    String str="select * from station where district = '"+district+"'";
    System.out.println(str);
    Iterator it = conn.getData(str).iterator();
    String id="",result="";
    if(it.hasNext()){
       
       while(it.hasNext()){
            Vector v = (Vector) it.next();
            
            id += v.get(0)+":";
            
            result += v.get(1)+":";
            
            
       }
       
       
       out.println(id+"#"+result);
       System.out.println(id+"#"+result);

        } else {
            out.println("failed");
        }
}



/////////////////////////////////////////////////////////////////////////////////

//   getComplaintReply
    if (key.equals("restaurant_list")) {
        String result = "",id = "";
//        String uid = request.getParameter("uid").toString();
        String qry = "SELECT  r.* FROM `login` l,`restaurant_register` r WHERE r.rid=l.`uid` and l.status = '1' ";
        System.out.println("qry  " + qry);
        Iterator itr = conn.getData(qry).iterator();
        
        if (itr.hasNext()) {
            while (itr.hasNext()) {
                Vector v = (Vector) itr.next();
                id += v.get(0)+"#";

                result += "RESTAURANT NAME      : " + v.get(2) +"\nOWNER NAME                  : " + v.get(3) + "\nLOCATION                         : " 
                        + v.get(4) +"\nDISTRICT                          : " + v.get(5) +"\nLOCATION                         : " + v.get(4) +"\nPHONE                               : " + v.get(6) 
                        +"\nEMAIL                                : " + v.get(7) +"\n"+  "#";

            }
            out.println(id+"///"+result);

        } else {
            out.println("failed");
        }

    }
    

//--------------------------reject_request--------------------------------------

if (key.equals("reject_restaurant")) {
        String rid = request.getParameter("rid");
        String qry = "delete from login where uid ='" + rid + "'  and type = 'restaurant'";
        String qry1 = "delete from restaurant_register where rid ='" + rid + "' ";
        System.out.println(qry);
        if (conn.putData(qry) > 0  && conn.putData(qry1) > 0) {

            out.print("successful");
        } else {
            out.print("failed");
        }
    }


//--------------------------action_restaurant--------------------------------------

if (key.equals("action_restaurant")) {
        String rid = request.getParameter("rid");
        String action = request.getParameter("action");
        String qry = "update login set  status = '"+action+"'  where uid ='" + rid + "'  and type = 'restaurant'";
        System.out.println(qry);
        if (conn.putData(qry) > 0 ) {

            out.print("successful");
        } else {
            out.print("failed");
        }
    }



/////////////////////////////////////////////////////////////////////////////////

//   getComplaintReply
    if (key.equals("restaurant_request_list")) {
        String result = "",id = "";
//        String uid = request.getParameter("uid").toString();
        String qry = "SELECT  r.* FROM `login` l,`restaurant_register` r WHERE r.rid=l.`uid` and l.status = '0' ";
        System.out.println("qry  " + qry);
        Iterator itr = conn.getData(qry).iterator();
        
        if (itr.hasNext()) {
            while (itr.hasNext()) {
                Vector v = (Vector) itr.next();
                id += v.get(0)+"#";

                result += "RESTAURANT NAME      : " + v.get(2) +"\nOWNER NAME                  : " + v.get(3) + "\nLOCATION                         : " 
                        + v.get(4) +"\nDISTRICT                           : " + v.get(5)+"\nPHONE                                : " 
                        + v.get(6) +"\nEMAIL                                 : " + v.get(7) +"\n"+  "#";

            }
            out.println(id+"///"+result);

        } else {
            out.println("failed");
        }

    }
    


//---------------------------------reg_delb--------------------//
if (key.equals("reg_delb")) {
        String name = request.getParameter("name");
        String latitude = request.getParameter("latitude");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String longitude = request.getParameter("longitude");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String district = request.getParameter("district");
        String aadhar = request.getParameter("aadhar");
        String stationid = request.getParameter("stationid");
        String u_id = request.getParameter("u_id");
        
        System.out.println("name " + name + " phone " + phone + " email " + email);

        String str = "select count(*) from login where  username = '" + phone + "'";
        System.out.println(str);
        Iterator it = conn.getData(str).iterator();
        if (it.hasNext()) {
            Vector v = (Vector) it.next();
            int max_vid = Integer.parseInt(v.get(0).toString());
            System.out.println(max_vid);
            if (max_vid == 0) {

                String qry = "INSERT INTO `delivery_boy`(`sid`,`station_id`,`name`,`district`,`aadhar`,`address`,`phone`,`email`,`lat`,`long`) VALUES ('" + u_id + "','" + stationid + "','" + name + "','" + district + "','" + aadhar + "','" + address + "','" + phone + "','" + email + "','" + latitude + "','" + longitude + "') ";
                String qry1 = "insert into login (`uid`,`username`,`password`,`type`,`status`)values((select max(did)from delivery_boy),'" + phone + "','" + password + "','delivery_boy',1)";
                if (conn.putData(qry) > 0 && conn.putData(qry1) > 0) {

                   
                         System.out.print("successful");
                        out.print("successful");
              

                }
                
            } else {
                out.print("failed");
            }

        } else {
            out.print("failed");
        }
    }
    


/////////////////////////////////////////////////////////////////////////////////

//   getComplaintReply
    if (key.equals("delivery_boy__list")) {
        String result = "",id = "";
        String u_id = request.getParameter("u_id");
//        String uid = request.getParameter("uid").toString();
        String qry = "SELECT  d.* FROM `login` l,`delivery_boy` d WHERE d.did=l.`uid` and l.status = '1' and d.sid= '"+u_id+"' AND l.`type`='delivery_boy' ";
        System.out.println("qry  " + qry);
        Iterator itr = conn.getData(qry).iterator();
        
        if (itr.hasNext()) {
            while (itr.hasNext()) {
                Vector v = (Vector) itr.next();
                id += v.get(0)+"#";

                result += "NAME                          : " + v.get(3) + "\nPHONE                        : " + v.get(7) +"\nEMAIL                         : " + v.get(8) 
                        +"\nDISTRICT                   : " + v.get(4)+"\nADHAR NUMBER      : " + v.get(5)  + 
                        "\nADDRESS                   : " +v.get(6)  +"\n"+  "#";

            }
            out.println(id+"///"+result);

        } else {
            out.println("failed");
        }

    }
    
//--------------------------reject_deliveryboy--------------------------------------

if (key.equals("reject_deliveryboy")) {
        String did = request.getParameter("did");
        String qry = "delete from login where uid ='" + did + "'  and type = 'delivery_boy'";
        String qry1 = "delete from delivery_boy where did ='" + did + "' ";
        System.out.println(qry);
        if (conn.putData(qry) > 0  && conn.putData(qry1) > 0) {

            out.print("successful");
        } else {
            out.print("failed");
        }
    }


////////////////////////////////////////////////////////////////////////////////
if(key.equals("cat_list")){
    
    String str="select * from category";
    System.out.println(str);
    String val="";
        String val1="";

    Iterator it = conn.getData(str).iterator();
    if(it.hasNext()){
    while(it.hasNext()){
        Vector v = (Vector) it.next();
        val += v.get(0)+":";
                val1 += v.get(1)+":";
       }
    String value = val+"#"+val1;
    out.println(value);

    System.out.println(value);
    }
    else{
        out.print("failed");
    }
}


///////////////////////////////////////////////////////////////////////////////
if(key.equals("add_product")){
        String pname = request.getParameter("pname");
        String prate = request.getParameter("prate");
        String pquan = request.getParameter("pquan");
        String pdetail = request.getParameter("pdetail");
        String category = request.getParameter("pcat"); 
        String u_id = request.getParameter("u_id");
        String type = request.getParameter("type");
        String base_string = request.getParameter("base_string");

        String str ="insert into product(`sid`,`name`,`category`,`type`,`description`,`price`,`stock`,`appimage`) values('"+u_id+"','"+pname+"','"+category+"','"+type+"','"+pdetail+"','"+prate+"','"+pquan+"','"+base_string+"')";
       if(conn.putData(str)>0)
       {
           out.print("success");
       }
       else{
           out.print("failed");
       }

}


if(key.equals("list_product")){
    String category = request.getParameter("cat_name");
    String res_id = request.getParameter("res_id");
    System.out.println(category);
    String val="";
    String val1="";
    String val2="";
    List<ProductPojo> allInfos = new ArrayList<ProductPojo>();
    
    String str = "select * from product where category='"+category+"' and sid = '"+res_id+"'";
    System.out.println(str);
    Iterator it = conn.getData(str).iterator();
    if(it.hasNext()){
    while(it.hasNext()){
        Vector v = (Vector) it.next();
        ProductPojo pro = new ProductPojo();
        pro.setPid(v.get(0).toString());
        pro.setSid(v.get(1).toString());
        pro.setName(v.get(2).toString());
        pro.setCategory(v.get(3).toString());
        pro.setType(v.get(4).toString());
        pro.setDescription(v.get(5).toString());
        pro.setPrice(v.get(6).toString());
        pro.setStock(v.get(7).toString());
        pro.setAppimage(v.get(8).toString());
        allInfos.add(pro);
        
//        val += v.get(0)+":";
//        val1 += "\nName : "+v.get(3)+"\nRate/hr : "+v.get(5)+"\nDetails  :"+v.get(6)+"&";
//        val2 += v.get(2)+":";
       }
    }
//    System.out.print(allInfos);
     Gson gson = new Gson();
     String infoall = gson.toJson(allInfos);
    
        if (infoall.equals("[]")) {
            out.println("failed");
        } else {
            out.println(infoall);
            System.out.println(infoall);
        }
//        System.out.println(infoall);
        
}


////////////////////////////////////////////////////////////////////////////////
if(key.equals("list_product_all")){
    String res_id = request.getParameter("res_id");
    String val="";
    String val1="";
    String val2="";
    List<ProductPojo> allInfos = new ArrayList<ProductPojo>();
    
    String str = "select * from product where sid = '"+res_id+"'";
    System.out.println(str);
    Iterator it = conn.getData(str).iterator();
    if(it.hasNext()){
    while(it.hasNext()){
        Vector v = (Vector) it.next();
        ProductPojo pro = new ProductPojo();
        pro.setPid(v.get(0).toString());
        pro.setSid(v.get(1).toString());
        pro.setName(v.get(2).toString());
        pro.setCategory(v.get(3).toString());
        pro.setType(v.get(4).toString());
        pro.setDescription(v.get(5).toString());
        pro.setPrice(v.get(6).toString());
        pro.setStock(v.get(7).toString());
        pro.setAppimage(v.get(8).toString());
        allInfos.add(pro);
        
//        val += v.get(0)+":";
//        val1 += "\nName : "+v.get(3)+"\nRate/hr : "+v.get(5)+"\nDetails  :"+v.get(6)+"&";
//        val2 += v.get(2)+":";
       }
    }
//    System.out.print(allInfos);
     Gson gson = new Gson();
     String infoall = gson.toJson(allInfos);
    
        if (infoall.equals("[]")) {
            out.println("failed");
        } else {
            out.println(infoall);
            System.out.println(infoall);
        }
//        System.out.println(infoall);
        
}


////////////////////////////////////////////////////////////////////////////////

if(key.equals("update_product")){
//        String pname = request.getParameter("pname");
//        String pbrand = request.getParameter("pbrand");
        String prate = request.getParameter("rate");
        String pquan = request.getParameter("quantity");
        String pdetail = request.getParameter("details");
        String p_id = request.getParameter("p_id");
        System.out.println(p_id);
           String str ="update product set `price`= '"+prate+"',`stock`='"+pquan+"',`description`='"+pdetail+"' where pid = '"+p_id+"'";
           System.out.println(str);
       if(conn.putData(str)>0)
       {
           out.print("success");
       }
       else{
           out.print("failed");
       }

}


////////////////////////////////////////////////////////////////////////////////
if(key.equals("view_product")){
    String p_id = request.getParameter("p_id");
    
    String val="";
    String val1="";
    String val2="";
    List<ProductPojo> allInfos = new ArrayList<ProductPojo>();
    
    String str = "select * from product where pid = '"+p_id+"'";
    System.out.println(str);
    Iterator it = conn.getData(str).iterator();
    if(it.hasNext()){
//    while(it.hasNext()){
       Vector v = (Vector) it.next();
        ProductPojo pro = new ProductPojo();
        pro.setPid(v.get(0).toString());
        pro.setSid(v.get(1).toString());
        pro.setName(v.get(2).toString());
        pro.setCategory(v.get(3).toString());
        pro.setType(v.get(4).toString());
        pro.setDescription(v.get(5).toString());
        pro.setPrice(v.get(6).toString());
        pro.setStock(v.get(7).toString());
        pro.setAppimage(v.get(8).toString());
        allInfos.add(pro);
        
        
//        val += v.get(0)+":";
//        val1 += "\nName : "+v.get(3)+"\nRate/hr : "+v.get(5)+"\nDetails  :"+v.get(6)+"&";
//        val2 += v.get(2)+":";
//       }
    }
//    System.out.print(allInfos);
     Gson gson = new Gson();
     String infoall = gson.toJson(allInfos);
    
        if (infoall.equals("[]")) {
            out.println("failed");
        } else {
            out.println(infoall);
        }
//        System.out.println(infoall);
        
}


/////////////////////////////////////////////////////////////////////////////
if(key.equals("delete_product")){
    String pid = request.getParameter("pid");
    String str = "delete from product where pid='"+pid+"'";
    if(conn.putData(str)>0){
        out.print("success");
    }else{
        out.print("failed");
    }
}


//-----------------------------------Add Stations-----------------------------//

if(key.equals("add_train")){
    
    String name = request.getParameter("name");
    String trainno = request.getParameter("trainno");
    String source = request.getParameter("source");
    System.out.println(name);
    String destination = request.getParameter("destination");

    String val="";
    
    
      String str = "select count(*) from train where  name = '" + name + "' and train_no = '"+trainno+"'";
        System.out.println(str);
        Iterator it = conn.getData(str).iterator();
        if (it.hasNext()) {
            Vector v = (Vector) it.next();
            int max_vid = Integer.parseInt(v.get(0).toString());
            System.out.println(max_vid);
            if (max_vid == 0) {

    String str1="insert into train(`name`,`from`,`to`,`train_no`) values ('"+name+"','"+source+"','"+destination+"','"+trainno+"')";
    System.out.println(str1);
    if(conn.putData(str1)>0){
        out.println("Success");
        
    }
    else{
       out.println("failed");
    }
    System.out.println(val);
        }else{
                
                 System.out.println("Already Existing");
       out.println("Already Existing");
    }

            
        }
        else{
       out.println("failed");
    }

}  




//--------------------------reject_request--------------------------------------

if (key.equals("reject_station")) {
        String stid = request.getParameter("stid");
        String qry = "delete from station where sid ='" + stid + "' ";
//        String qry1 = "delete from restaurant_register where rid ='" + rid + "' ";
        System.out.println(qry);
        if (conn.putData(qry) > 0) {

            out.print("successful");
        } else {
            out.print("failed");
        }
    }


//--------------------------stations_list---------------------------------------//
if(key.equals("train_list")){
    
    String str="select * from train";
    System.out.println(str);
    Iterator it = conn.getData(str).iterator();
    String district="",id="",lat="",longg="",name="",address="",result="";
    if(it.hasNext()){
       
       while(it.hasNext()){
            Vector v = (Vector) it.next();
           
            id += v.get(0)+":";
            
            result += "NAME                     : "+v.get(1)+"\nTrain Number       : "+v.get(2)+"\nFrom                       : "+v.get(3)+"\nTO                           : "+v.get(4)+"///";
            
            
       }
       
       
       out.println(id+"#"+result);
       System.out.println(id);

        } else {
            out.println("failed");
        }
}


//--------------------------reject_train--------------------------------------

if (key.equals("reject_train ")) {
        String trid = request.getParameter("trid");
        String qry = "delete from train where tid ='" + trid + "' ";
//        String qry1 = "delete from restaurant_register where rid ='" + rid + "' ";
        System.out.println(qry);
        if (conn.putData(qry) > 0) {

            out.print("successful");
        } else {
            out.print("failed");
        }
    }




/////////////////////////////////////////////////////////////////////////////////

//   getComplaintReply
    if (key.equals("restaurant_list_stationvise")) {
        String result = "",id = "";
        String stidval = request.getParameter("stidval").toString();
        String qry = "SELECT  r.* FROM `login` l,`restaurant_register` r WHERE r.rid=l.`uid` and l.status = '1' and r.station_id = '"+stidval+"' ";
        System.out.println("qry  " + qry);
        Iterator itr = conn.getData(qry).iterator();
        
        if (itr.hasNext()) {
            while (itr.hasNext()) {
                Vector v = (Vector) itr.next();
                id += v.get(0)+"#";

                result += "\nRESTAURANT NAME      : " + v.get(2) +"\nOWNER NAME           : " + v.get(3) + "\nLOCATION             : " + v.get(4) +"\nDISTRICT             : " + v.get(5) +"\n\nPHONE                : " + v.get(6) +"\nEMAIL                : " + v.get(7) +"\n"+  "#";

            }
            out.println(id+"///"+result);
            System.out.println(id+"///"+result);
        } else {
            out.println("failed");
        }

    }
    

////////////////////////////////////////////////////////////////////////////////
    if (key.equals("add_cart")) {
        String result = "";
        String uid = request.getParameter("uid").toString();
        String pro_id = request.getParameter("pro_id").toString();
        String res_id = request.getParameter("res_id").toString();
        String total = request.getParameter("total").toString();
        String quantity = request.getParameter("quantity").toString();
        System.out.println(java.time.LocalDate.now());
        String date = java.time.LocalDate.now().toString();

        String str = "select count(*) from cart where user_id = '" + uid + "' and status='not paid'";
        System.out.println(str);
        Iterator it = conn.getData(str).iterator();

        if (it.hasNext()) {
            Vector v = (Vector) it.next();
            int max_vid = Integer.parseInt(v.get(0).toString());
            System.out.println(max_vid);

            if (max_vid == 0) {

                String str1 = "select sid,price,stock from product where pid = '" + pro_id + "'";
                Iterator it1 = conn.getData(str1).iterator();
                if (it1.hasNext()) {

                    Vector v1 = (Vector) it1.next();
                    String restaurant_id = v1.get(0).toString();

//                HttpSession session_pro=request.getSession();  
                    session.setAttribute("restaurant_id", restaurant_id);

                    String res_val = (String) session.getAttribute("restaurant_id");
//                    System.out.println(cat_val);

                    Integer totalval = Integer.parseInt(total);
                    Integer quan = Integer.parseInt(quantity);

//                    Integer total = rate * quan;s

                    Integer stock = Integer.parseInt(v1.get(2).toString());
                    Integer stock_update = stock - quan;

//                    System.out.println(category);
                    System.out.println(total.toString());
//                
//                String str2 = "SELECT IDENT_CURRENT('payment')+1";
//                Iterator it2 = conn.getData(str2).iterator();
//                if (it2.hasNext()) {
//                Vector v2 = (Vector) it2.next();
//                String auto_val =v2.get(0).toString();

                    String str4 = "insert into payment(`u_id`,`res_id`,`posted_date`) values('" + uid + "','" + res_id + "','" + date + "')";

                    String str5 = "insert into cart(user_id,res_id,pro_id,pay_id,quantity,amount,posted_date) values('" + uid + "','" + res_id + "','" + pro_id + "',(select max(p_id) from payment),'" + quan + "','" + total + "','" + date + "')";
                    String str6 = "update product set stock = '" + stock_update + "' where pid = '" + pro_id + "'";
                    if (conn.putData(str4) > 0 && conn.putData(str5) > 0 && conn.putData(str6) > 0) {
                        System.out.println("Successfull");
                        out.println("Successfull");
                    } else {
                        out.println("failed");
                    }

//                String str2 = "insert into payment(`u_id`,`total_amount`,`timestamp`,`status`) values('"+uid+"','0',)";
                }

            } else {

                String strcat = "select p.sid ,c.pay_id from cart c, product p where c.user_id = '" + uid + "' and c.pro_id = p.pid and c.status='not paid'";
                System.out.println(strcat);
                Iterator itcat = conn.getData(strcat).iterator();
                if (itcat.hasNext()) {

                    Vector v_cat = (Vector) itcat.next();
                    String cart_res_id = v_cat.get(0).toString();
                    String pay_id = v_cat.get(1).toString();

                    String str1 = "select sid,price,stock from product where pid = '" + pro_id + "'";
                    System.out.println(str1);
                    Iterator it1 = conn.getData(str1).iterator();
                    if (it1.hasNext()) {

                        Vector v1 = (Vector) it1.next();

                        if (res_id.equals(cart_res_id)) {
//                            Integer rate = Integer.parseInt();
                            Integer quan = Integer.parseInt(quantity);

//                            Integer total = rate * quan;

                            Integer stock = Integer.parseInt(v1.get(2).toString());
                            Integer stock_update = stock - quan;

                            System.out.println(total.toString());

//                String str4 = "insert into payment(`u_id`,`posted_date`) values('"+uid+"','"+date+"')";
                            String str5 = "insert into cart(user_id,res_id,pro_id,pay_id,quantity,amount,posted_date) values('" + uid + "','" + res_id + "','" + pro_id + "','" + pay_id + "','" + quan + "','" + total + "','" + date + "')";
                            String str6 = "update product set stock = '" + stock_update + "' where pid = '" + pro_id + "'";
                            if (conn.putData(str5) > 0 && conn.putData(str6) > 0) {
                                System.out.println("Successfull");
                                out.println("Successfull");
                            }
//                else{
//                   out.println("failed"); 
//                } 

                        } else {
                            out.println("Please Complete your Ordering from one Restaurant. Otherwise Please Clear Cart List");
                        }

                    }

//        String qry = "SELECT * FROM `bank` WHERE `u_id`='" + uid + "'";
//        System.out.println("qry  " + qry);
//        Iterator itr = conn.getData(qry).iterator();
//        if (itr.hasNext()) {
//            Vector v = (Vector) itr.next();
//
//            result = v.get(2) + "#" + v.get(3) + "#" + v.get(4) + "#" + v.get(5);
//            out.println(result);
//        } else {
//            out.println("failed");
//        }
                }
            }
        }
    }
    

///////////////////////////////////////////////////////////////////////////////
if (key.equals("list_cart")) {
        String result = "";
        String uid = request.getParameter("uid").toString();
        System.out.println(java.time.LocalDate.now());
        String date = java.time.LocalDate.now().toString();
        List<ProductPojo> allInfos = new ArrayList<ProductPojo>();

        String str = "select distinct p.sid,c.amount,c.quantity,p.name,p.type,p.stock,p.price,p.appimage,p.pid,c.cart_id,p.description,c.pay_id, r.`station_id` from cart c, product p,`restaurant_register` r  where c.user_id = '" + uid + "' and c.pro_id = p.pid and c.status='not paid' and c.posted_date = '"+date+"' AND c.`res_id` = r.`rid` AND p.`sid` = r.`rid`";
//        System.out.println(str);
//                String c_amount="",c_quantity="",p_name="",p_brand="",p_quantity="",p_rate="",p_appimage="";
        System.out.println(str);

        Iterator it = conn.getData(str).iterator();

//        if (it.hasNext()) {
        while (it.hasNext()) {
            Vector v = (Vector) it.next();

            ProductPojo bean = new ProductPojo();
            bean.setSid(v.get(0).toString());
            bean.setAmount(v.get(1).toString());
            bean.setQuantity(v.get(2).toString());
            bean.setName(v.get(3).toString());
            bean.setType(v.get(4).toString());
            bean.setStock(v.get(5).toString());
            bean.setPrice(v.get(6).toString());
            bean.setAppimage(v.get(7).toString());
            bean.setPid(v.get(8).toString());
            bean.setCart_id(v.get(9).toString());
            bean.setDescription(v.get(10).toString());
            bean.setPay_id(v.get(11).toString());
            bean.setStation_id(v.get(12).toString());
            allInfos.add(bean);
            System.out.println(allInfos);

        }
        Gson gson = new Gson();
        String infoall = gson.toJson(allInfos);
        if (infoall.equals("[]")) {
            out.println("failed");
        } else {
            out.println(infoall);
        }
        System.out.println(infoall);
    }



//////////////////////////////////////////////////////////////////////////////////
    if (key.equals("delete_cart_pro")) {
        String result = "";
        String uid = request.getParameter("uid").toString();
        String cart_id = request.getParameter("cart_id").toString();
        String p_id = request.getParameter("p_id").toString();
        String quantity = request.getParameter("quantity").toString();
        String stock = request.getParameter("stock").toString();

        Integer quan_val = Integer.parseInt(quantity);
        Integer stock_val = Integer.parseInt(stock);
        Integer new_stock = 0;
        new_stock += stock_val + quan_val;
        String qry = "update product set stock = '" + new_stock + "' WHERE `pid`='" + p_id + "'";
        String qry1 = "delete from cart where cart_id = '" + cart_id + "'";

        System.out.println("qry  " + qry + " " + qry1);

        if (conn.putData(qry) > 0 && conn.putData(qry1) > 0) {

            out.println("successfull");
        } else {
            out.println("failed");
        }

    }
    


////////////////////////////////////////////////////////////////////////////////
    if (key.equals("account_check")) {
        String uid = request.getParameter("uid").toString();
        String totalcash = request.getParameter("totalcash").toString();
        System.out.println(totalcash+ " "+uid);
        String str1 = "select count(*) from bank where u_id = '" + uid + "'";
        System.out.println(str1);
        Iterator itr = conn.getData(str1).iterator();
        if (itr.hasNext()) {
            Vector v = (Vector) itr.next();
            int max_vid = Integer.parseInt(v.get(0).toString());
            System.out.println(max_vid);

            if (max_vid == 0) {
                out.println("Add Your Account Details");
            } else {
                String str = "SELECT * FROM bank where u_id = '"+uid+"'";
                System.out.println(str);
                String respo = "";
                Iterator itr1 = conn.getData(str).iterator();
                if (itr1.hasNext()) {
                    Vector vv = (Vector) itr1.next();
                    Integer deposit = Integer.parseInt(vv.get(5).toString());
                    System.out.println(deposit);
                    Integer total = Integer.parseInt(totalcash);
                    System.out.println(total);

                    System.out.println(total + " " + deposit);
                    if (deposit < total) {
                        out.println("failed");
                    } else {
                        respo = vv.get(0) + "#" + vv.get(3) + "#" + vv.get(4) + "#" + vv.get(5)+"#"+ vv.get(6) ;
                        out.println(respo);
                    }

                } else {
                    out.println("failed");
                }
            }

        }
    }



//--------------------------stations_list---------------------------------------//
if(key.equals("train_list_for_user")){
    
    String str="select * from train";
    System.out.println(str);
    Iterator it = conn.getData(str).iterator();
    String district="",id="",from="",to="",name="",trainno="",result="";
    if(it.hasNext()){
       
       while(it.hasNext()){
            Vector v = (Vector) it.next();
            district += v.get(2)+":";
            id += v.get(0)+":";
            name += v.get(1)+":";
           
            trainno += v.get(4)+":";
//            result += "\nNAME              : "+v.get(1)+"\nTrain No        : "+v.get(4)+"///";
            
            
       }
       
       
       out.println(id+"#"+name);
       System.out.println(id+"#"+name+"#"+result);

        } else {
            out.println("failed");
        }
}


/////////////////////////////////////////////////////////////////////////////////
    if (key.equals("payment")) {
        String uid = request.getParameter("uid").toString();
        String tot = request.getParameter("total").toString();
        String pay_id = request.getParameter("pay_id").toString();
        String b_id = request.getParameter("b_id").toString();
        String bal = request.getParameter("bal").toString();
        
        
        
        
        String station = request.getParameter("station").toString();
        String train = request.getParameter("train").toString();
        String coach = request.getParameter("coach").toString();
        String seat = request.getParameter("seat").toString();    
        

        Integer balance = Integer.parseInt(bal);
        Integer total = Integer.parseInt(tot);
        Integer new_balance = balance - total;
        String str1 = "update bank set balance = '" + new_balance.toString() + "' where b_id='" + b_id + "'";
        System.out.println(str1);

        String str2 = "update payment set total_amount = '" + total + "',status='paid', station = '" + station + "', train = '" + train + "', coach = '" + coach + "', seat = '" + seat + "' where p_id='" + pay_id + "'";
        System.out.println(str2);

        String str3 = "update cart set status='paid' where pay_id='" + pay_id + "' and user_id = '" + uid + "'";
        System.out.println(str3);
//        
//         String str4 = "insertinto bookings cart set status='paid' where pay_id='" + pay_id + "' and user_id = '" + uid + "'";
//        System.out.println(str3);

        if (conn.putData(str1) > 0 && conn.putData(str2) > 0 && conn.putData(str3) > 0) {
            out.println("successfull");
        } else {
            out.println("failed");

        }

    }
    

///////////////////////////////////////////////////////////////////////////////////////////////
    if (key.equals("payment_list")) {
        String uid = request.getParameter("uid").toString();
        String data = "", info = "";
String date = java.time.LocalDate.now().toString();
        String str1 = "select * from payment p , `restaurant_register` r where p.u_id = '" + uid + "' and p.status = 'paid' and p.posted_date ='"+date+"' and p.res_id = r.`rid` order by p.p_id desc";
        System.out.println(str1);

        List<ProductPojo> allInfos = new ArrayList<ProductPojo>();

        Iterator itr1 = conn.getData(str1).iterator();
        if (itr1.hasNext()) {
            while (itr1.hasNext()) {
                Vector v = (Vector) itr1.next();

                ProductPojo bean = new ProductPojo();
                bean.setP_id(v.get(0).toString());
                bean.setU_id(v.get(1).toString());
                bean.setRes_id(v.get(2).toString());
                bean.setTotal_amount(v.get(3).toString());
                bean.setPosted_date(v.get(4).toString());
                bean.setStatus(v.get(5).toString());
                bean.setStation(v.get(6).toString());
                bean.setTrain(v.get(7).toString());
                bean.setCoach(v.get(8).toString());
                bean.setSeat(v.get(9).toString());
                bean.setDelivery_status(v.get(10).toString());
                bean.setDeliveryboy(v.get(11).toString());
                bean.setStation_id(v.get(12).toString());
                bean.setRes_name(v.get(14).toString());
                bean.setPhone(v.get(18).toString());
                allInfos.add(bean);
                System.out.println(allInfos);

            }
            Gson gson = new Gson();
            String infoall = gson.toJson(allInfos);
            System.out.println(infoall);
            if (infoall.equals("[]")) {
                System.out.println("failed");
                out.println("failed");
            } else {
                out.println(infoall);
                System.out.println(infoall);
            }
            System.out.println(infoall);
        }
        else{
            out.println("failed");
        }
    }



//////////////////////////////////////////////////////////////////////////////////
if (key.equals("purchased_list")) {
    String uid = request.getParameter("uid").toString();
    String p_id = request.getParameter("p_id").toString();

    String str1 = "select distinct c.amount,c.quantity,p.name,p.price from cart c, product p where c.user_id = '" + uid + "' and c.pro_id = p.pid and c.pay_id = '" + p_id + "' and c.status='paid'";
    System.out.println(str1);
    String data = "";
    int i = 1;

    Iterator itr1 = conn.getData(str1).iterator();
    if (itr1.hasNext()) {
        while (itr1.hasNext()) {
            Vector v = (Vector) itr1.next();
            data += i + ".   " + v.get(2)
                    + "\n     Quantity  : ".concat(v.get(1).toString())
                    + "\n     Amount  :  " + v.get(0) + "#";

            i++;

        }
        out.println(data);
    } else {
        out.println("failed");

    }

}



//////////////////////////////////////////////////////////////////////////////////
//if (key.equals("restaurant_view_order_list")) {
//    String res_id = request.getParameter("res_id").toString();
//String date = java.time.LocalDate.now().toString();
//    String str1 = "SELECT DISTINCT c.cart_id,c.user_id,c.pro_id,c.pay_id FROM  cart c,payment pay, product p, user_registration u WHERE c.res_id = '"+res_id+"' AND  c.res_id = pay.res_id AND c.`res_id` = p.`sid` AND  c.pay_id = pay.p_id AND c.pro_id = p.pid  AND c.status='paid' AND c.`status` = pay.`status` AND c.`user_id`= pay.`u_id` AND c.`user_id` = u.`uid` and c.posted_date = '"+date+"' and c.posted_date = pay.posted_date";
//    
////    String str2 = "SELECT DISTINCT * FROM  cart c,payment pay WHERE c.res_id = '5' AND  c.res_id = pay.res_id AND  c.pay_id = pay.p_id AND c.status='paid' AND c.`status` = pay.`status`";
//    
//    System.out.println(str1);
//    String data = "";
//    int i = 1;
//
//    Iterator itr1 = conn.getData(str1).iterator();
//    if (itr1.hasNext()) {
//        while (itr1.hasNext()) {
//            Vector v = (Vector) itr1.next();
//            data += i + ".   " + v.get(2)
//                    + "\n     Quantity  : ".concat(v.get(1).toString())
//                    + "\n     Amount  :  " + v.get(0) + "#";
//
//            i++;
//
//        }
//        out.println(data);
//    } else {
//        out.println("failed");
//
//    }
//
//}



///////////////////////////////////////////////////////////////////////////////////////////////
    if (key.equals("restaurant_view_order_list")) {
        String uid = request.getParameter("uid").toString();
        String data = "", info = "";
String date = java.time.LocalDate.now().toString();
        String str1 = "select * from payment p , `user_registration` u where p.res_id = '" + uid + "' and p.status = 'paid' and p.posted_date ='"+date+"' and p.u_id = u.`uid` and delivery_status ='not delivered' order by p.p_id desc";
        System.out.println(str1);

        List<ProductPojo> allInfos = new ArrayList<ProductPojo>();

        Iterator itr1 = conn.getData(str1).iterator();
        if (itr1.hasNext()) {
            while (itr1.hasNext()) {
                Vector v = (Vector) itr1.next();

                ProductPojo bean = new ProductPojo();
                bean.setP_id(v.get(0).toString());
                bean.setU_id(v.get(1).toString());
                bean.setRes_id(v.get(2).toString());
                bean.setTotal_amount(v.get(3).toString());
                bean.setPosted_date(v.get(4).toString());
                bean.setStatus(v.get(5).toString());
                bean.setStation(v.get(6).toString());
                bean.setTrain(v.get(7).toString());
                bean.setCoach(v.get(8).toString());
                bean.setSeat(v.get(9).toString());
                bean.setDelivery_status(v.get(10).toString());
                bean.setDeliveryboy(v.get(11).toString());
                bean.setUser_name(v.get(13).toString());
                bean.setPhone(v.get(16).toString());
                allInfos.add(bean);
                System.out.println(allInfos);

            }
            Gson gson = new Gson();
            String infoall = gson.toJson(allInfos);
            System.out.println(infoall);
            if (infoall.equals("[]")) {
                System.out.println("failed");
                out.println("failed");
            } else {
                out.println(infoall);
                System.out.println(infoall);
            }
            System.out.println(infoall);
        }
        else{
            out.println("failed");
        }
    }


//////////////////////////////////////////////////////////////////////////////////
if (key.equals("station_name")) {
    String station_id = request.getParameter("station_id").toString();

    String str1 = "select name from station where sid = '" + station_id + "' ";
    System.out.println(str1);
    String data = "";
    int i = 1;

    Iterator itr1 = conn.getData(str1).iterator();
    if (itr1.hasNext()) {
        Vector v = (Vector) itr1.next();
       data = v.get(0).toString();
        out.println(data);
    } else {
        out.println("failed");

    }

}


//--------------------------assign_deliveryboy--------------------------------------

if (key.equals("assign_deliveryboy")) {
        String did = request.getParameter("did");
        String pay_id = request.getParameter("pay_id");
        String cust_id = request.getParameter("cust_id");
        String date = java.time.LocalDate.now().toString();
        String qry1 = "update payment set delivery_status = 'Order Assigned',deliveryboy = '"+did+"' where p_id = '"+pay_id+"' and u_id = '"+cust_id+"' and posted_date = '"+date+"'";
//        String qry = "delete from login where uid ='" + did + "'  and type = 'delivery_boy'";
//        String qry1 = "delete from delivery_boy where did ='" + did + "' ";
        System.out.println(qry1);
        if (conn.putData(qry1) > 0) {

            out.print("Successfully Assigned");
        } else {
            out.print("failed");
        }
    }



///////////////////////////////////////////////////////////////////////////////////////////////
    if (key.equals("restaurant_view_order_delivered_list")) {
        String uid = request.getParameter("uid").toString();
        String data = "", info = "";
String date = java.time.LocalDate.now().toString();
        String str1 = "select * from payment p , `user_registration` u where p.res_id = '" + uid + "' and p.status = 'paid' and p.posted_date ='"+date+"' and p.u_id = u.`uid` and delivery_status ='Order Assigned' order by p.p_id desc";
        System.out.println(str1);

        List<ProductPojo> allInfos = new ArrayList<ProductPojo>();

        Iterator itr1 = conn.getData(str1).iterator();
        if (itr1.hasNext()) {
            while (itr1.hasNext()) {
                Vector v = (Vector) itr1.next();

                ProductPojo bean = new ProductPojo();
                bean.setP_id(v.get(0).toString());
                bean.setU_id(v.get(1).toString());
                bean.setRes_id(v.get(2).toString());
                bean.setTotal_amount(v.get(3).toString());
                bean.setPosted_date(v.get(4).toString());
                bean.setStatus(v.get(5).toString());
                bean.setStation(v.get(6).toString());
                bean.setTrain(v.get(7).toString());
                bean.setCoach(v.get(8).toString());
                bean.setSeat(v.get(9).toString());
                bean.setDelivery_status(v.get(10).toString());
                bean.setDeliveryboy(v.get(11).toString());
                bean.setUser_name(v.get(13).toString());
                bean.setPhone(v.get(16).toString());
                allInfos.add(bean);
                System.out.println(allInfos);

            }
            Gson gson = new Gson();
            String infoall = gson.toJson(allInfos);
            System.out.println(infoall);
            if (infoall.equals("[]")) {
                System.out.println("failed");
                out.println("failed");
            } else {
                out.println(infoall);
                System.out.println(infoall);
            }
            System.out.println(infoall);
        }
        else{
            out.println("failed");
        }
    }




//////////////////////////////////////////////////////////////////////////////////
if (key.equals("assigned_del_boy_detail")) {
    String p_id = request.getParameter("p_id").toString();

    String str1 = "select d.* from payment p ,delivery_boy d where p_id ='" + p_id + "' and p.deliveryboy = d.did  ";
    System.out.println(str1);
    String data = "",name ="", phone;
    int i = 1;

    Iterator itr1 = conn.getData(str1).iterator();
    if (itr1.hasNext()) {
        Vector v = (Vector) itr1.next();
       data = v.get(0).toString()+"#"+v.get(3).toString()+"#"+v.get(6).toString()+"#"+v.get(7).toString()+"#"+v.get(8).toString()+"#";
       
        out.println(data);
    } else {
        out.println("failed");

    }

}


//----------------------res_getdetail---------------------------------------
if(key.equals("res_getdetail")){
    String uid = request.getParameter("uid").toString();
    System.out.println(uid);
    String data = "";
    String str="select s.name , r.`name`,r.`owner`,r.`location`,r.`district`,r.`phone`,r.`email` from delivery_boy d, restaurant_register r, station s where d.did = '"+uid+"' and d.sid = r.rid  and d.station_id = s.sid and r.station_id = d.station_id AND r.`station_id` = s.`sid`" ;
    System.out.println(str);
    Iterator it = conn.getData(str).iterator();
    String dist="";
    if(it.hasNext()){
       
       while(it.hasNext()){
            Vector v = (Vector) it.next();
            data += v.get(0)+":"+v.get(1)+":"+v.get(2)+":"+v.get(3)+":"+v.get(4)+":"+v.get(5)+":"+v.get(6);
       }
       
       
       out.println(data);
       System.out.println(data);

        } else {
            out.println("failed");
        }
}

////////////////////////////////////////////////////////////////////////////////


 if (key.equals("delivery_view_order_list")) {
        String uid = request.getParameter("uid").toString();
        String data = "", info = "";
String date = java.time.LocalDate.now().toString();
        String str1 = "select * from payment p , `user_registration` u where p.deliveryboy = '" + uid + "' and p.status = 'paid' and p.delivery_status = 'Order Assigned' and p.posted_date ='"+date+"' and p.u_id = u.`uid` order by p.p_id desc";
        System.out.println(str1);

        List<ProductPojo> allInfos = new ArrayList<ProductPojo>();

        Iterator itr1 = conn.getData(str1).iterator();
        if (itr1.hasNext()) {
            while (itr1.hasNext()) {
                Vector v = (Vector) itr1.next();

                ProductPojo bean = new ProductPojo();
                bean.setP_id(v.get(0).toString());
                bean.setU_id(v.get(1).toString());
                bean.setRes_id(v.get(2).toString());
                bean.setTotal_amount(v.get(3).toString());
                bean.setPosted_date(v.get(4).toString());
                bean.setStatus(v.get(5).toString());
                bean.setStation(v.get(6).toString());
                bean.setTrain(v.get(7).toString());
                bean.setCoach(v.get(8).toString());
                bean.setSeat(v.get(9).toString());
                bean.setDelivery_status(v.get(10).toString());
                bean.setDeliveryboy(v.get(11).toString());
                bean.setUser_name(v.get(13).toString());
                bean.setPhone(v.get(16).toString());
                allInfos.add(bean);
                System.out.println(allInfos);

            }
            Gson gson = new Gson();
            String infoall = gson.toJson(allInfos);
            System.out.println(infoall);
            if (infoall.equals("[]")) {
                System.out.println("failed");
                out.println("failed");
            } else {
                out.println(infoall);
                System.out.println(infoall);
            }
            System.out.println(infoall);
        }
        else{
            out.println("failed");
        }
    }


//////////////////////////////////////////////////////////////////////////////////
if (key.equals("user_travelling_detail")) {
    
    String uid = request.getParameter("uid").toString();
    String p_id = request.getParameter("p_id").toString();

    String str1 = "select p.coach , p.seat , s.name , t.name from payment p ,station s,train t where p.p_id ='" + p_id + "' and p.station = s.sid and p.train = t.tid  ";
    System.out.println(str1);
    String data = "",name ="", phone;
    int i = 1;

    Iterator itr1 = conn.getData(str1).iterator();
    if (itr1.hasNext()) {
        Vector v = (Vector) itr1.next();
       data = v.get(0).toString()+"#"+v.get(1).toString()+"#"+v.get(2).toString()+"#"+v.get(3).toString();
       
        out.println(data);
    } else {
        out.println("failed");

    }

}




//--------------------------mark_as_delivered--------------------------------------

if (key.equals("mark_as_delivered")) {
        String p_id = request.getParameter("p_id");
        String date = java.time.LocalDate.now().toString();
        String qry1 = "update payment set delivery_status = 'Order Delivered' where p_id = '"+p_id+"' and posted_date = '"+date+"'";
//        String qry = "delete from login where uid ='" + did + "'  and type = 'delivery_boy'";
//        String qry1 = "delete from delivery_boy where did ='" + did + "' ";
        System.out.println(qry1);
        if (conn.putData(qry1) > 0) {

            out.print("Successfully Delivered");
        } else {
            out.print("failed");
        }
    }



///////////////////////////////////////////////////////////////////////////////////////////////
    if (key.equals("delivery_view_order_history")) {
        String uid = request.getParameter("uid").toString();
        String data = "", info = "";
String date = java.time.LocalDate.now().toString();
        String str1 = "select * from payment p , `user_registration` u where p.deliveryboy = '" + uid + "' and p.status = 'paid' and p.posted_date ='"+date+"' and p.u_id = u.`uid` and p.delivery_status ='Order Delivered' order by p.p_id desc";
        System.out.println(str1);

        List<ProductPojo> allInfos = new ArrayList<ProductPojo>();

        Iterator itr1 = conn.getData(str1).iterator();
        if (itr1.hasNext()) {
            while (itr1.hasNext()) {
                Vector v = (Vector) itr1.next();

                ProductPojo bean = new ProductPojo();
                bean.setP_id(v.get(0).toString());
                bean.setU_id(v.get(1).toString());
                bean.setRes_id(v.get(2).toString());
                bean.setTotal_amount(v.get(3).toString());
                bean.setPosted_date(v.get(4).toString());
                bean.setStatus(v.get(5).toString());
                bean.setStation(v.get(6).toString());
                bean.setTrain(v.get(7).toString());
                bean.setCoach(v.get(8).toString());
                bean.setSeat(v.get(9).toString());
                bean.setDelivery_status(v.get(10).toString());
                bean.setDeliveryboy(v.get(11).toString());
                bean.setUser_name(v.get(13).toString());
                bean.setPhone(v.get(16).toString());
                allInfos.add(bean);
                System.out.println(allInfos);

            }
            Gson gson = new Gson();
            String infoall = gson.toJson(allInfos);
            System.out.println(infoall);
            if (infoall.equals("[]")) {
                System.out.println("failed");
                out.println("failed");
            } else {
                out.println(infoall);
                System.out.println(infoall);
            }
            System.out.println(infoall);
        }
        else{
            out.println("failed");
        }
    }





    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
