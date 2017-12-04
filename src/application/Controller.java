package application;

import dbService.DBException;
import dbService.DBService;
import interfaces.impls.CollectionQuestionAnswer;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import objects.QuestionAndAnswer;

public class Controller {
    CollectionQuestionAnswer collectionQuestionAnswerImpl = new CollectionQuestionAnswer();
    DBService dbService = new DBService();

    @FXML
    private Button OKButton;

    @FXML
    private TableColumn<QuestionAndAnswer, String> coloumnQuestion;

    @FXML
    private TableColumn<QuestionAndAnswer, String> coloumnAnswer;

    @FXML
    private TableColumn<QuestionAndAnswer, String> coloumnkeyWord;

    @FXML
    private TableView mainTableView;

    @FXML
    private Label labelCount;

    @FXML
    private TextField answerField;

    @FXML
    private TextField questionField;

    @FXML
    private TextField keyWordField;

    @FXML
    private TextField searchTestField;

    @FXML
    private void initialize() {
        mainTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        mainTableView.setEditable(true);

        coloumnAnswer.setCellValueFactory(new PropertyValueFactory<QuestionAndAnswer, String>("answer"));
        coloumnAnswer.setCellFactory(TextFieldTableCell.forTableColumn());
        coloumnAnswer.setEditable(true);
        coloumnAnswer.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<QuestionAndAnswer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<QuestionAndAnswer, String> event) {
                ((QuestionAndAnswer) event.getTableView().getItems().get(
                        event.getTablePosition().getRow())
                ).setAnswer(event.getNewValue());
            }
        });
        coloumnQuestion.setCellValueFactory(new PropertyValueFactory<QuestionAndAnswer, String>("question"));
        coloumnQuestion.setCellFactory(TextFieldTableCell.forTableColumn());
        coloumnQuestion.setEditable(true);
        coloumnQuestion.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<QuestionAndAnswer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<QuestionAndAnswer, String> event) {
                ((QuestionAndAnswer) event.getTableView().getItems().get(
                        event.getTablePosition().getRow())
                ).setQuestion(event.getNewValue());
            }
        });

        coloumnkeyWord.setCellValueFactory(new PropertyValueFactory<QuestionAndAnswer, String>("keyWord"));
        coloumnkeyWord.setCellFactory(TextFieldTableCell.forTableColumn());
        coloumnkeyWord.setEditable(true);
        coloumnkeyWord.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<QuestionAndAnswer, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<QuestionAndAnswer, String> event) {
                ((QuestionAndAnswer) event.getTableView().getItems().get(
                        event.getTablePosition().getRow())
                ).setKeyWord(event.getNewValue());
            }
        });


        collectionQuestionAnswerImpl.getQuestionAndAnswerList().addListener(new ListChangeListener<QuestionAndAnswer>() {
            @Override
            public void onChanged(Change<? extends QuestionAndAnswer> c) {
                updateControlLabel();
            }
        });

        collectionQuestionAnswerImpl.fillTestData();
        mainTableView.setItems(collectionQuestionAnswerImpl.getQuestionAndAnswerList());


        FilteredList<QuestionAndAnswer> filteredData = new FilteredList<>(collectionQuestionAnswerImpl.getQuestionAndAnswerList(), p -> true);

        searchTestField.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    filteredData.setPredicate(QuestionAndAnswer -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();

                        if (QuestionAndAnswer.getAnswer().toLowerCase()
                                .contains(lowerCaseFilter)) {
                            return true;
                        } else if (QuestionAndAnswer.getQuestion().toLowerCase()
                                .contains(lowerCaseFilter)) {
                            return true;
                        }else if (QuestionAndAnswer.getKeyWord().toLowerCase()
                                .contains(lowerCaseFilter)) {
                            return true;
                        }
                        return false;
                    });
                });
        SortedList<QuestionAndAnswer> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(mainTableView.comparatorProperty());
        mainTableView.setItems(sortedData);
    }

    public void onClickAction(javafx.event.ActionEvent actionEvent) throws DBException {
        //System.out.println("Hello!");
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        Button clickedButton = (Button) source;

        QuestionAndAnswer questionAndAnswer = (QuestionAndAnswer) mainTableView.getSelectionModel().getSelectedItem();

        switch (clickedButton.getId()) {
            case "searchButton":
//                if(searchTestField.getText().equals("")){
//                    collectionQuestionAnswerImpl.getQuestionAndAnswerList();
//                }else{
//                    // 3. Wrap the FilteredList in a SortedList.
//
//                    SortedList<QuestionAndAnswer> sortedData = new SortedList<QuestionAndAnswer>(mainTableView);
//
//                    // 4. Bind the SortedList comparator to the TableView comparator.
//                    sortedData.comparatorProperty().bind(mainTableView.comparatorProperty());
//                    // 5. Add sorted (and filtered) data to the table.
//                    mainTableView.setItems(sortedData);
//                }

                break;
            case "OKButton":
                QuestionAndAnswer newLine = new QuestionAndAnswer(questionField.getText(), answerField.getText(), keyWordField.getText());
                addQuestionAndAnswer(newLine);

                System.out.println("Adding: " + newLine);
                break;
        }
    }

    private void addQuestionAndAnswer(QuestionAndAnswer questionAndAnswer) throws DBException {
        collectionQuestionAnswerImpl.add(questionAndAnswer);
        Long id = dbService.addQuestionAndAnswerData(questionAndAnswer);
        if(id.equals(0)){
            System.out.println("Ошибка при добавлении записи");
        }else{
            System.out.println("Добавлена запись с номером " + id.toString());
        }

    }

    private void updateControlLabel() {
        labelCount.setText("Количество записей:" + collectionQuestionAnswerImpl.getQuestionAndAnswerList().size());
    }

}
