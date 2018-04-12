package tdt4140.gr1832.app.ui;

import java.io.IOException;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tdt4140.gr1832.app.core.CommentContainer;
import tdt4140.gr1832.app.core.FeedbackContainer;
import tdt4140.gr1832.app.core.TrainerTrainingCommentApp;

public class TrainerTrainingCommentController extends WindowController {

    @FXML
    private StackPane root;
    
    private TrainerTrainingCommentApp commentApp;
    
    @FXML
    Label messageLabel2;
    
    private int editId = -1;
    
    private String editContent = "";
        
    private boolean isProgram;
    
    private boolean isProgram2;
    
    private String choose;
    
    private int id;
    
    @FXML
    JFXButton changeCommentBackButton;
    
    @FXML
    JFXButton changeCommentButton;
    
    @FXML
    JFXTextArea changeCommentArea;
    
    @FXML
    JFXTextField changeCommentField;
    
    @FXML
    Label commentToLabel;
    
    @FXML
    Label messageLabel;
    
    @FXML
    JFXTextArea commentTextArea;
    
    @FXML
    JFXListView<String> givenComments;
    
    @FXML
    JFXComboBox<String> progUserComboBox;
    
    @FXML
    JFXComboBox<String> progUserComboBox2;
    
    @FXML
    JFXComboBox<String> comboBox2;
    
    private ObservableList<String> commentList = FXCollections.observableArrayList();
    private ObservableList<String> feedbackList = FXCollections.observableArrayList();
    private ObservableList<String> progUserList = FXCollections.observableArrayList();
    private ObservableList<String> programs = FXCollections.observableArrayList();
    private ObservableList<String> users = FXCollections.observableArrayList();
    
    

    @FXML
    public void initialize() {
    		changeCommentVisibility(false);
    		progUserList.addAll("Program", "Medlem");
    		progUserComboBox2.setItems(progUserList);
    		progUserComboBox.setItems(progUserList);
    		commentApp = new TrainerTrainingCommentApp();
    		root.setPickOnBounds(false);
    		commentApp.requestCommentGiven();
    		commentApp.requestPrograms();
    		commentApp.requestFeedbackGiven();
    		commentApp.requestUsers();
    		comboBox2.setVisible(false);
    		commentTextArea.setVisible(false);
    		givenComments.setVisible(false);
    		
    		 		
    }
    
    @FXML
    public void editComment() {
    		String choose = givenComments.getSelectionModel().getSelectedItem();
    		if (choose.length() > 0) {
    			String[] sections = choose.split(":");
    			String[] sections2 = sections[0].split("\n");
    			if (isProgram2) {
    				editId = commentApp.getCommentId(sections2[1].trim(), sections[1].trim());
    			} else {
    				editId = commentApp.getFeedbackId(sections2[1].trim(), sections[1].trim());
    			}
    			changeCommentArea.setText(sections[1]);
    			changeCommentField.setText(sections2[0]);
    			changeCommentVisibility(true);
    		}
    	
    	}
    
    @FXML
    public void changeComment() {
    		String editContent = changeCommentArea.getText();
    		if (editContent.length() > 0) {
    			boolean done = false;
    			if (isProgram2) {
    				done = commentApp.updateComment(editId, editContent);
    				this.addCommentsToCommentList(commentApp.getCommentsFromTrainer());
    				givenComments.getItems().clear();
        			givenComments.setItems(commentList);
    			} else {
    				done = commentApp.updateFeedback(editId, editContent);
    				this.addFeedbacksToFeedbackList(commentApp.getFeedbacksFromTrainer());
    				givenComments.getItems().clear();
    	    			givenComments.setItems(feedbackList);
    			}
    			if(done) {
    				messageLabel2.setVisible(true);
    				messageLabel2.setTextFill(Color.web("#000000"));
    				messageLabel2.setText("Kommentarer er oppdatert");
    			}else {
    				messageLabel2.setVisible(true);
    				messageLabel2.setTextFill(Color.web("#ff0000"));
    				messageLabel2.setText("Noe gikk galt, prøv igjen senere");
    			}
    		}
    		changeCommentArea.setText("");
    		changeCommentField.setText("");
    		changeCommentVisibility(false);
    }
    
    @FXML
    public void changeCommentBack() {
    		editId = -1;
    		editContent = "";
    		changeCommentVisibility(false);
    }
    
    private void addCommentsToCommentList(List<CommentContainer> list) {
    		commentList.clear();
    		for (CommentContainer c : list) {
    			commentList.add(commentApp.getProgramById(c.getProgramID())+ "\n"+c.getDate() +": "+ c.getContent());
    		}
    }
    
    private void addFeedbacksToFeedbackList(List<FeedbackContainer> list) {
    		feedbackList.clear();
		for (FeedbackContainer c : list) {
			feedbackList.add(commentApp.getUserById(c.getUserID())+ "\n"+c.getDate() +": "+ c.getContent());
		}
}
    
    @FXML
    public void handleProgUserComboBox() {
    		comboBox2.setVisible(true);
    		String valg = progUserComboBox.getSelectionModel().getSelectedItem();
    		if ("Program".equals(valg)) {
    			comboBox2.setPromptText("Velg program");
    			programs.addAll(commentApp.getProgramNames());
    			comboBox2.setItems(programs);
    			isProgram = true;
    			
    		} else if ("Medlem".equals(valg)) {
    			comboBox2.setPromptText("Velg medlem");
    			users.addAll(commentApp.getUserNames());
    			comboBox2.setItems(users);
    			isProgram = false;
    		}
    }
    @FXML
    public void handleComboBox2() {
	    	String selected = comboBox2.getSelectionModel().getSelectedItem();
	    	choose = selected;
	    	commentTextArea.setVisible(true);
	    	
	    	if (isProgram) {
	    		id = commentApp.getIdFromProgramName(selected);
	    		commentTextArea.setPromptText("Skriv inn kommentar til program: " + selected );
	    		
	    	} else {
	    		id = commentApp.getIdFromUsername(selected);
	    		commentTextArea.setPromptText("Skriv inn kommentar til medlem: " + selected );
	    	}
    }
    
    @FXML
    public void handleProgUserComboBox2() {
    		givenComments.setVisible(true);
    		String valg = progUserComboBox2.getSelectionModel().getSelectedItem();
    		if ("Program".equals(valg)) {
    			this.addCommentsToCommentList(commentApp.getCommentsFromTrainer());
    			givenComments.getItems().clear();
    			givenComments.setItems(commentList);
    			isProgram2 = true;
    		} else if ("Medlem".equals(valg)) {
    			this.addFeedbacksToFeedbackList(commentApp.getFeedbacksFromTrainer());
    			givenComments.getItems().clear();
    			givenComments.setItems(feedbackList);
    			isProgram2 = false;
    		}
    }

    
    @FXML
    public void giveComment() {
    		String content = commentTextArea.getText();
    		if (content.length() > 0) {
    			if (isProgram) {
    				if (commentApp.makeCommentToGroup(id, content)) {
    					messageLabel.setTextFill(Color.web("#000000"));
    					messageLabel.setText("Kommentaren er sendt inn for programmet " + choose);
    					commentTextArea.setText(""); 
    					this.addCommentsToCommentList(commentApp.getCommentsFromTrainer());
    	    				givenComments.getItems().clear();
    	    				givenComments.setItems(commentList);
    					}else {
    					messageLabel.setTextFill(Color.web("#ff0000"));
    					messageLabel.setText("Noe gikk galt, prøv igjen senere.");
    					}
    				}
    			 else {
    				if (commentApp.makeFeedbackToUser(content, id)) {
    					messageLabel.setTextFill(Color.web("#000000"));
    					messageLabel.setText("Kommentaren er sendt inn til bruker " + choose);
    					commentTextArea.setText("");
    					this.addFeedbacksToFeedbackList(commentApp.getFeedbacksFromTrainer());
    	    				givenComments.getItems().clear();
    	    				givenComments.setItems(feedbackList);
    				} else {
    					messageLabel.setTextFill(Color.web("#ff0000"));
    					messageLabel.setText("Noe gikk galt, prøv igjen senere.");
    					}
    			 	}
    			}
    		}
    
    @FXML
    public void loadDialog(ActionEvent parentEvent) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Logg ut bekreftelse"));
        content.setBody(new Text("Er du sikker på at du vil logge ut?"));
        JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER);
        JFXButton buttonYes = new JFXButton("Ja");
        JFXButton buttonNo = new JFXButton("Nei");

        buttonYes.setOnAction((event) -> {
            dialog.close();
            try {
                NavigerTilSide("LoginScreen.fxml", parentEvent);
                FxApp.getAS().DUMMYsetuser(null);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

        buttonNo.setOnAction((event) -> {
            dialog.close();
        });
        content.setActions(buttonYes, buttonNo);
        dialog.show();
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TrainerTrainingComment.fxml"));
        
        stage.setTitle("FXML Welcome");
        stage.setScene(new Scene(root, 1200, 660));
        stage.show();
    }
    
    private void changeCommentVisibility(boolean value) {
    		changeCommentArea.setVisible(value);
		changeCommentField.setVisible(value);
		commentToLabel.setVisible(value);
		changeCommentButton.setVisible(value);
		changeCommentBackButton.setVisible(value);
		givenComments.setVisible(! value);
		
    }

    public static void main(String[] args) {
        launch(args);
    }


}
