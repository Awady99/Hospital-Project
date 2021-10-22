/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.hospital;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundImage;
//import javafx.scene.layout.BackgroundPosition;
//import javafx.scene.layout.BackgroundRepeat;
//import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author mar00
 */
public class ProjectHospital extends Application {

    @Override
    public void start(final Stage primaryStage) {

        //scene 14 for sign up
        Label name = new Label("Name:");
        Label id = new Label("Id:");
        Label pass = new Label("Password:");
        TextField tname = new TextField();
        final TextField tid = new TextField();
        final TextField tpass = new TextField();
        Button sign = new Button("SignUp");

        Button backs = new Button("Back");
        GridPane si = new GridPane();
        si.add(name, 0, 1);
        si.add(id, 0, 2);
        si.add(pass, 0, 3);
        si.add(tname, 1, 1);
        si.add(tid, 1, 2);
        si.add(tpass, 1, 3);
        si.add(sign, 1, 4);
        si.add(backs, 4, 15);
        si.setAlignment(Pos.CENTER);
        si.setHgap(10);
        si.setVgap(10);
        final Scene f = new Scene(si, 500, 700);

        sign.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    //connection 3shan ydef ally fy al signup
                    Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/mar00/Documents/NetBeansProjects/project hospital/hospital.db");
                    PreparedStatement s = con.prepareStatement("Insert into users (ID,Password) values (?,?)");

                    s.setInt(1, Integer.parseInt(tid.getText()));
                    s.setInt(2, Integer.parseInt(tpass.getText()));

                    s.execute();
                    System.out.println("Data Inserted");
                } catch (SQLException ex) {
                    Logger.getLogger(ProjectHospital.class.getName()).log(Level.SEVERE, null, ex);
                }
                ;
            }
        });

        // scene for login or signup
        Button signup = new Button("sign up");
        Button login = new Button("login");

        GridPane gfirst = new GridPane();
        gfirst.add(signup, 0, 0);
        gfirst.add(login, 1, 0);

        gfirst.setAlignment(Pos.CENTER);
        gfirst.setHgap(10);

        final Scene first = new Scene(gfirst, 500, 700);
        primaryStage.setScene(first);

        signup.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(f);
            }
        });
        backs.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(first);
            }
        });

        Button btn = new Button();
        btn.setText("login");
        btn.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        Label l = new Label();

        l.setText("ID :");
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        Label l2 = new Label();
        l2.setText("Password :");
        l2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        final TextField f1 = new TextField();
        final PasswordField f2 = new PasswordField();
        f1.setPromptText("enter the id");
        f1.setFocusTraversable(false);

        f2.setPromptText("enter the password");
        f2.setFocusTraversable(false);
        GridPane g = new GridPane();
        g.add(l, 0, 0);
        g.add(l2, 0, 1);
        g.add(f1, 1, 0);
        g.add(f2, 1, 1);
        g.add(btn, 1, 2);
        g.setAlignment(Pos.CENTER);
        g.setHgap(10);
        g.setVgap(10);
        //g.setStyle("-fx-background-image: url('1.jpg');-fx-background-repeat:stretch;-fx-background-size:1000 1000;");
//        Image i = new Image("doctor.jpg", 1000, 1000, true, true);
//        BackgroundImage im = new BackgroundImage(i, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
//        g.setBackground(new Background(im));
//        g.setMaxHeight(1000);
        //    g.setMaxWidth(1000);
//        ImageView img = new ImageView("1.jpg");
//        img.setScaleX(2);
        //      img.setScaleY(5);
        // img.setStyle("-fx-background-size:inherit");
        // g.setStyle("-fx-background-size:inherit");
        //StackPane stack = new StackPane();
        //  stack.getChildren().addAll(img, g);
        //stack.setPadding(new Insets(0, 0, 0, 0));
        //stack.setMinSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //scene two
        Button b1 = new Button("DOCTOR");
        Button b2 = new Button("PATIENT");
        Button b3 = new Button("ROOMS");

        GridPane g1 = new GridPane();
        g1.add(b1, 0, 0);
        g1.add(b2, 1, 0);
        g1.add(b3, 2, 0);
        g1.setAlignment(Pos.CENTER);
        g1.setHgap(10);
        g1.setVgap(10);
        final Scene s = new Scene(g1, 500, 700);
        final Scene scene = new Scene(g, 500, 700);

        login.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                primaryStage.setScene(scene);

            }
        });

        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                try {
                    Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/mar00/Documents/NetBeansProjects/project hospital/hospital.db");
                    Statement st = con.createStatement();
                    st.execute("Select ID , Password from users where ID = '" + Integer.parseInt(f1.getText()) + "'and Password = '" + f2.getText() + "'");
                    ResultSet rs = st.getResultSet();
                    while (rs.next()) {
                        if (rs.getInt("ID") == Integer.parseInt(f1.getText()) && rs.getInt("Password") == Integer.parseInt(f2.getText())) {

                            primaryStage.setScene(s);

                        } else {
                            System.out.println("Invalid username or password");
                        }
//                         Label k=new Label("Error Invalid Username Or Password");
//                          k.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
//                                 GridPane w = new GridPane();
//                                 w.add(k, 0, 0);
//                                 Scene m =new Scene(w,500,700);                   }

                    }

                    //connection check that name and pass is true if true open scene s
                } catch (SQLException ex) {
                    System.out.println("Error ");
                }
            }
        });

        //scene 3 for doctors
        Button bs = new Button("select");
//        Button bu = new Button("update");
        Button bd = new Button("delete");
        Button bi = new Button("insert");
        GridPane g2 = new GridPane();
        g2.add(bs, 0, 0);
//        g2.add(bu, 1, 0);
        g2.add(bd, 2, 0);
        g2.add(bi, 3, 0);
        g2.setAlignment(Pos.CENTER);
        g2.setHgap(10);
        g2.setVgap(10);
        Button back = new Button("Back to home");
        g2.add(back, 4, 20);
        g2.setAlignment(Pos.CENTER);
        final Scene s3 = new Scene(g2, 500, 700);
        back.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene);
            }
        });
      //  scene 4 for update
//        Label lu = new Label();
//        lu.setText("update data for doctors");
//        lu.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
//        lu.setAlignment(Pos.CENTER);
//        Label lu1 = new Label("Name:");
//        Label lu2 = new Label("id:");
//
//        final TextField t1 = new TextField();
//        TextField t2 = new TextField();
////
//        GridPane g4 = new GridPane();
//        Button b4 = new Button("ok");
//        g4.add(lu, 1, 0);
//        g4.add(lu1, 0, 1);
//        g4.add(lu2, 0, 2);
//
//        g4.add(t1, 1, 1);
//        g4.add(t2, 1, 2);
//
//        g4.add(b4, 1, 4);
//        g4.setAlignment(Pos.CENTER);
//        g4.setVgap(10);
//        g4.setHgap(10);
//         Button back1 =new Button("Back to home");
//        g4.add(back1, 4, 20);
//        g4.setAlignment(Pos.CENTER);
////        
//        final Scene s4 = new Scene(g4, 700, 700);
//        back1.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent event) {
//                primaryStage.setScene(s);
//            }
//        });

//        b4.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent event) {
//                try {
//                    Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/mar00/Documents/NetBeansProjects/project hospital/hospital.db");
//                    PreparedStatement s = con.prepareStatement("update doctors set name= ? where ID = ?");
//                  s.setString(1, t1.getText());
//                  s.setString(2,);
//when he update the data of doc by name or id or ha
        //button ok
//                } catch (SQLException ex) {
//                    Logger.getLogger(ProjectHospital.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
        //scene 5 when click insert
        Label li = new Label();
        li.setText("insert data of doctors");
        li.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        li.setAlignment(Pos.CENTER);
        Label li1 = new Label("Name:");
        Label li2 = new Label("id:");
        Label li3 = new Label("Dep:");
        Button back2 = new Button("Back");
        final Label li4 = new Label();
        final TextField ti1 = new TextField();
        final TextField ti2 = new TextField();
        final TextField ti3 = new TextField();
        GridPane g5 = new GridPane();
        Button b5 = new Button();
        b5.setText("ok insert");
        b5.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        g5.add(li, 1, 0);
        g5.add(li1, 0, 1);
        g5.add(li2, 0, 2);
        g5.add(li3, 0, 3);
        g5.add(ti1, 1, 1);
        g5.add(ti2, 1, 2);
        g5.add(ti3, 1, 3);

        g5.add(b5, 1, 4);
        g5.add(li4, 1, 7);
        g5.setAlignment(Pos.CENTER);
        g5.setVgap(10);
        g5.setHgap(10);

//         Button back2 =new Button("Back to home");
        g5.add(back2, 4, 20);
        g5.setAlignment(Pos.CENTER);
        final Scene s5 = new Scene(g5, 700, 700);
        back2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(s3);
            }
        });

        b5.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
//                try {
//                    Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/mar00/Documents/NetBeansProjects/project hospital/hospital.db");
//
//
//                    PreparedStatement s = con.prepareStatement("Insert into doctors (name,id,dep) values (?,?,?)");
//
//                    s.setString(1, ti1.getText());
//                    s.setString(2, ti2.getText());
//                    s.setString(3, ti3.getText());
//                    s.execute();
//                    System.out.println("Data Inserted");
////connection when he insrt new doc with name and id and dep
//                } catch (SQLException ex) {
//                    System.out.println("Error" + ex.toString());
//                }

                Doctors d = new Doctors(ti1.getText(), Integer.parseInt(ti2.getText()), ti3.getText());
                Doctors.insert(d);

                li4.setText("Data of the Doctor Inserted !");

            }
        });

        //scene 6 for select
        Label ls = new Label();
        ls.setText("Select data of any doctor by ID");
        ls.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        Label l6 = new Label("ID:");
        final TextField t6 = new TextField();
        Button b6 = new Button();
        Button back3 = new Button("Back");
        b6.setText("okay");
        b6.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        GridPane g6 = new GridPane();
        g6.add(ls, 1, 0);
        g6.add(l6, 0, 1);
        g6.add(t6, 1, 1);
        g6.add(b6, 1, 2);
        g6.setAlignment(Pos.CENTER);
        g6.setHgap(10);
        g6.setVgap(10);
        final Label inf = new Label();
        g6.add(inf, 2, 2);

        g6.add(back3, 4, 20);
        g6.setAlignment(Pos.CENTER);
        final Scene s6 = new Scene(g6, 700, 700);
        back3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(s3);
            }
        });

//final Label inf=new Label();
        b6.setOnAction(new EventHandler<ActionEvent>() { //when click okay the name and id and department of doc show in the run
            //connection
            public void handle(ActionEvent event) {
//                try {
//                   
//                    Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/mar00/Documents/NetBeansProjects/project hospital/hospital.db");
////                
//                    Statement s = con.createStatement();
//                    s.execute("select * from doctors where ID = " + Integer.parseInt(t6.getText()));
//
//                    ResultSet rs = s.getResultSet();
//                    while (rs.next()) {
//
//                        System.out.println(rs.getString("Name") + " " + rs.getInt("ID") + " " + rs.getString("Dep"));
//                    }
//
//                } catch (SQLException ex) {
//                    System.out.println("Error in Selecting  " + ex.toString());
//                }

                Doctors d = Doctors.select(Integer.parseInt(t6.getText()));

                inf.setText(d.getName() + " " + d.getId() + " " + d.getDep());

//                 Doctors.select(Integer.parseInt(t6.getText()));
            }
        });

        //scene 7 for delete 
        Label ld = new Label();
        ld.setText("delete data of any doctor by ID");
        ld.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        Label l7 = new Label("ID:");
        final TextField t7 = new TextField();
        Button b7 = new Button();
        final Label ldel = new Label();
        b7.setText("okay delete");
        b7.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        GridPane g7 = new GridPane();
        g7.add(ld, 1, 0);
        g7.add(l7, 0, 1);
        g7.add(t7, 1, 1);
        g7.add(b7, 1, 2);
        g7.add(ldel, 1, 7);
        g7.setAlignment(Pos.CENTER);
        g7.setHgap(10);
        g7.setVgap(10);

        Button back4 = new Button("Back");
        g7.add(back4, 4, 20);
        g7.setAlignment(Pos.CENTER);
        final Scene s7 = new Scene(g7, 700, 700);
        back4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(s3);
            }
        });

        b7.setOnAction(new EventHandler<ActionEvent>() {//connection when he click okay delete database delete all data about the name that he enter
            public void handle(ActionEvent event) {

//                try {
//                    Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/mar00/Documents/NetBeansProjects/project hospital/hospital.db");
//                    PreparedStatement s = con.prepareStatement("delete from doctors where ID= ?");
//                    s.setInt(1, Integer.parseInt(t7.getText()));
//                    s.execute();
//                    System.out.println("Delete Done!");
//
//
//                } catch (SQLException ex) {
//                    System.out.println("Error deleting " + ex.toString());
//                }
                Doctors d = Doctors.delete(Integer.parseInt(t7.getText()));
                ldel.setText("Data deleted!");
            }
        });

        //scene 8 when click patient 
        Button ps = new Button("search");
        Button pu = new Button("update");
        Button pd = new Button("delete");
        Button pi = new Button("insert");
        GridPane g8 = new GridPane();
        g8.add(ps, 0, 0);
        g8.add(pu, 1, 0);
        g8.add(pd, 2, 0);
        g8.add(pi, 3, 0);
        g8.setAlignment(Pos.CENTER);
        g8.setHgap(10);
        g8.setVgap(10);

        // Button back5 =new Button("Back to home");
        //g8.add(back5, 4, 20);
        //g8.setAlignment(Pos.CENTER);
        final Scene s8 = new Scene(g8, 700, 700);
         //back5.setOnAction(new EventHandler<ActionEvent>() {
        // public void handle(ActionEvent event) {
        //   primaryStage.setScene(s);
        //}
        //});

        //scene 9 when clik update of patient
        Label u = new Label();
        u.setText("update Status for patient search for him By ID");
        u.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        u.setAlignment(Pos.CENTER);
        Label u1 = new Label("ID:");
        Label u2 = new Label("New Status:");

        final TextField tu = new TextField();
        final TextField tu2 = new TextField();

        GridPane g9 = new GridPane();
        Button b9 = new Button("done");
        g9.add(u, 1, 0);
        g9.add(u1, 0, 1);
        g9.add(u2, 0, 2);

        g9.add(tu, 1, 1);
        g9.add(tu2, 1, 2);

        g9.add(b9, 1, 4);
        final Label res = new Label();
        g9.add(res, 1, 7);
        g9.setAlignment(Pos.CENTER);
        g9.setVgap(10);
        g9.setHgap(10);
        // Button back6 =new Button("Back to home");
        //g9.add(back6, 4, 20);
        //g9.setAlignment(Pos.CENTER);
        final Scene s9 = new Scene(g9, 700, 700);
         //back6.setOnAction(new EventHandler<ActionEvent>() {
        // public void handle(ActionEvent event) {
        //   primaryStage.setScene(s);
        // }
        //  });

        b9.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/mar00/Documents/NetBeansProjects/project hospital/hospital.db");
                    PreparedStatement s = con.prepareStatement("Update patient set Status = ? where ID = ?");
                    s.setInt(2, Integer.parseInt(tu.getText()));
                    s.setString(1, tu2.getText());
                    s.execute();
                    res.setText("Update Done to the Patient !");
                    System.out.println("Update Done to the Patient !");

//connection  when he update data for patient by name or id 
                } catch (SQLException ex) {
                    res.setText("Error in Updating Patient due to " + ex.toString());
                    System.out.println("Error in Updating Patient due to " + ex.toString());
                }
            }
        });

        //scene 10 when click insert of patient
        Label m = new Label();
        m.setText("insert data of patient");
        m.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        m.setAlignment(Pos.CENTER);
        Label l0 = new Label("Name:");
        Label l10 = new Label("id:");
        Label l3 = new Label("status:");
        final Label lins = new Label();
        final TextField t0 = new TextField();
        final TextField t10 = new TextField();
        final TextField t3 = new TextField();
        GridPane g10 = new GridPane();
        Button b10 = new Button();
        b10.setText("okay insert");
        b10.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        g10.add(m, 1, 0);
        g10.add(l0, 0, 1);
        g10.add(l10, 0, 2);
        g10.add(l3, 0, 3);
        g10.add(t0, 1, 1);
        g10.add(t10, 1, 2);
        g10.add(t3, 1, 3);
        g10.add(lins, 1, 7);
        g10.add(b10, 1, 4);
        g10.setAlignment(Pos.CENTER);
        g10.setVgap(10);
        g10.setHgap(10);
        //Button back7 =new Button("Back to home");
        //g10.add(back7, 4, 20);
        //g10.setAlignment(Pos.CENTER);
        final Scene s10 = new Scene(g10, 700, 700);
         //back7.setOnAction(new EventHandler<ActionEvent>() {
        // public void handle(ActionEvent event) {
        //   primaryStage.setScene(s);
        //}
        //});

        b10.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                Patient p = new Patient(t0.getText(), Integer.parseInt(t10.getText()), t3.getText());
                Patient.insert(p);
                lins.setText("Data of the patient Inserted");
            }
        });

        //scene 11 when click search from patient 
        Label lse = new Label();
        lse.setText("Search data of any patient by ID");
        lse.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        Label l11 = new Label("ID:");
        final TextField t11 = new TextField();
        Button b11 = new Button();
        b11.setText("okay search");
        b11.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        final Label info = new Label();

        GridPane g11 = new GridPane();
        g11.add(info, 2, 2);
        g11.add(lse, 1, 0);
        g11.add(l11, 0, 1);
        g11.add(t11, 1, 1);
        g11.add(b11, 1, 2);
        g11.setAlignment(Pos.CENTER);
        g11.setHgap(10);
        g11.setVgap(10);
        // Button back8 =new Button("Back to home");
        //g11.add(back8, 4, 20);
        //g11.setAlignment(Pos.CENTER);
        final Scene s11 = new Scene(g11, 700, 700);
         //back8.setOnAction(new EventHandler<ActionEvent>() {
        //  public void handle(ActionEvent event) {
        //    primaryStage.setScene(s);
        //}
        //});

        ps.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(s11);
            }
        });

        b11.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                Patient p = Patient.select(Integer.parseInt(t11.getText()));
                info.setText(p.getName() + " " + p.getId() + " " + p.getStatus());

            }
        });

        //scene 12 when click delete from patient
        Label ld2 = new Label();
        ld2.setText("delete data of patient by ID of the patient");
        ld2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        Label l12 = new Label("ID:");

        final TextField t12 = new TextField();
        Button b12 = new Button();
        b12.setText("ok delete");
        b12.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        final Label sho = new Label();
        GridPane g12 = new GridPane();
        g12.add(sho, 2, 2);
        g12.add(ld2, 1, 0);
        g12.add(l12, 0, 1);
        g12.add(t12, 1, 1);
        g12.add(b12, 1, 2);

        g12.setAlignment(Pos.CENTER);
        g12.setHgap(10);
        g12.setVgap(10);

        final Scene s12 = new Scene(g12, 700, 700);
        pd.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(s12);
            }
        });

        b12.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
//                try {
//                    Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/mar00/Documents/NetBeansProjects/project hospital/hospital.db");
//                    PreparedStatement s = con.prepareStatement("delete from patient where ID= ?");
//                    s.setInt(1, Integer.parseInt(t12.getText()));
//                    s.execute();
//                    System.out.println("Delete Done!");
//
//                    //connection when he want to delet data of any paient he enter the name and then delete all data of this name when he click ok delete
//                } catch (SQLException ex) {
//                    System.out.println("Error in Deleting data " + ex.toString());
//                }

                Patient p = Patient.delete(Integer.parseInt(t12.getText()));
                if (Patient.delFlag == 1) {
                    sho.setText("Patient Data Deleted !");
                } else {
                    sho.setText("Error in Deleting data");
                }
            }
        });

        //scene 13 for rooms 
        Label r = new Label("search in rooms");
        Label r2 = new Label("enter the room number:");
        final TextField r3 = new TextField();
        Button r4 = new Button("search ");
        final Label lsea = new Label();
        GridPane r1 = new GridPane();
        r1.add(lsea, 1, 7);
        r1.add(r, 0, 0);
        r1.add(r2, 0, 1);
        r1.add(r3, 1, 1);
        r1.add(r4, 1, 2);
        r1.setAlignment(Pos.CENTER);
        r1.setHgap(10);
        r1.setVgap(10);
        final Scene s13 = new Scene(r1, 500, 700);
        r.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));

        r4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
//                try {
//                    // connection when he want to search about rooms he inter the number and check if the room is empty or not and display massage
//                    Connection con = DriverManager.getConnection("jdbc:sqlite:C:/Users/mar00/Documents/NetBeansProjects/project hospital/hospital.db");
//                    Statement st = con.createStatement();
//                    st.execute("Select * from rooms where roomnum = " + Integer.parseInt(r3.getText()));
//                    ResultSet rs = st.getResultSet();
//                    while (rs.next()) {
//                        if (rs.getInt("Check") == 0) {
//
//                            System.out.println("This room is Available");
//                        } else {
//                            System.out.println("This room is not Available");
//                        }
//
//                    }
//
//                } catch (SQLException ex) {
//                    System.out.println("Error in searching rooms " + ex.toString());
//                }

                rooms r = rooms.selectroom(Integer.parseInt(r3.getText()));

                if (rooms.check == 0) {
                    lsea.setText("room is available");
                } else if (rooms.check == 1) {

                    lsea.setText("room is not available");

                }

            }
        });

        b3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(s13);
            }
        });

        pi.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(s10);
            }
        });

        pu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(s9);
            }
        });

        b2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(s8);
            }
        });

        bs.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(s6);
            }
        });

        bi.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(s5);
            }
        });
//        bu.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent event) {
//                primaryStage.setScene(s4);
//            }
//        });
        b1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(s3);
            }
        });

        bd.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.setScene(s7);
            }
        });

      ///  Scene scene2 = new Scene(stack, 500, 700);
        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMinHeight(500);
        primaryStage.setMinHeight(500);
    }

    public static void main(String[] args) {
        launch(args);

    }

}
