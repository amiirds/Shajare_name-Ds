package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;



public class Controller {
    private final String about = "برای درست کار کردن پروژه باید در هنگام ساختن کابر جدید تمام ورودی ها وارد شود..\n\nدر این پروژه از گراف خطی استفاده شده است که از این جهت که نسبت به گراف جدولی دارای سرعت عمل و مصرف حافظه کمتری میباشد، از نظر مهندسی نرم افزار گزینه مناسب تری است...\n\n این گراف شامل یک لیست آرایه ای از کلاس Person است که هر شی از این کلاس نیز دارای یک شی حاوی لیست هایی با مقادیر کلاس های Person است که هر کدام به نحوی با شی مادر ارتباط فامیلی دارند...\n\nطراح و نویسنده: امیررضا دلاوران شیراز\t (98111309)";



    VBox addRoot = new VBox();
    HBox hb1 = new HBox();
    TextField IDT = new TextField();
    Label L1 = new Label("ID");
    HBox hb2 = new HBox();
    TextField firstNameT = new TextField();
    Label L2 = new Label("نام");
    HBox hb3 = new HBox();
    TextField lastNameT = new TextField();
    Label L3 = new Label("نام خانوادگی");
    HBox hb4 = new HBox();
    TextField tavT = new TextField();
    Label L4 = new Label("تاریخ تولد");
    HBox hb5 = new HBox();
    TextField vafT = new TextField();
    Label L5 = new Label("تاریخ وفات (اختیاری)");
    HBox hb6 = new HBox();
    TextField dadT = new TextField();
    Label L6 = new Label("ID پدر");
    HBox hb7 = new HBox();
    TextField momT = new TextField();
    Label L7 = new Label("ID مادر");
    HBox hb8 = new HBox();
    TextField partnerT = new TextField();
    Label L8 = new Label("ID همسران (با , جدا کنید)");
    HBox hb9 = new HBox();
    RadioButton isMale = new RadioButton("مرد");
    RadioButton isFemale = new RadioButton("زن");
    ToggleGroup type = new ToggleGroup();
    Button save = new Button("ذخیره");


    HBox searchRoot = new HBox();
    VBox searchVBox = new VBox();
    TextField searchIDT = new TextField();
    RadioButton isID = new RadioButton("جستجو با ID");
    TextField searchNameT = new TextField();
    RadioButton isName = new RadioButton("جستجو با نام");
    Button searchB = new Button("جستجو");
    ToggleGroup searchT = new ToggleGroup();
    VBox ratioVBox = new VBox();
    ToggleGroup ratioT = new ToggleGroup();
    RadioButton R1 = new RadioButton("پدر");
    RadioButton R2 = new RadioButton("مادر");
    RadioButton R3 = new RadioButton("برادر");
    RadioButton R4 = new RadioButton("خواهر");
    RadioButton R5 = new RadioButton("پسر");
    RadioButton R6 = new RadioButton("دختر");
    RadioButton R7 = new RadioButton("زن");
    RadioButton R8 = new RadioButton("شوهر");
    RadioButton R9 = new RadioButton("دایی");
    RadioButton R10 = new RadioButton("خاله");
    RadioButton R11 = new RadioButton("عمو");
    RadioButton R12 = new RadioButton("عمه");
    RadioButton R13 = new RadioButton("باجناق");
    RadioButton R14 = new RadioButton("جاری");
    RadioButton R15 = new RadioButton("هوو");

    TextArea resultText = new TextArea();

    BorderPane waitPane = new BorderPane();
    Label waitingL = new Label("لطفاً صبر نمایید...");


    @FXML AnchorPane root;
    @FXML ListView<Person> listView;
    @FXML Button add;
    @FXML Button babies;
    @FXML Button search;
    @FXML Button getter;
    @FXML Button nonRatio;
    @FXML Button graphInf;

    {
        System.out.println("SHSJARE_NAME Project is Started...");

        AnchorPane.setBottomAnchor(addRoot, 0.0);
        AnchorPane.setLeftAnchor(addRoot, 30.0);
        AnchorPane.setRightAnchor(addRoot, 30.0);
        AnchorPane.setTopAnchor(addRoot, 5.0);
        addRoot.setSpacing(10);

        L1.setTextFill(Color.RED);
        L2.setTextFill(Color.RED);
        L3.setTextFill(Color.RED);
        L4.setTextFill(Color.RED);
        L5.setTextFill(Color.RED);
        L6.setTextFill(Color.RED);
        L7.setTextFill(Color.RED);
        L8.setTextFill(Color.RED);
        isMale.setTextFill(Color.RED);
        isFemale.setTextFill(Color.RED);
        save.setTextFill(Color.RED);

        hb1.setSpacing(10);
        hb1.getChildren().add(IDT);
        hb1.getChildren().add(L1);

        hb2.setSpacing(10);
        hb2.getChildren().add(firstNameT);
        hb2.getChildren().add(L2);

        hb3.setSpacing(10);
        hb3.getChildren().add(lastNameT);
        hb3.getChildren().add(L3);

        hb4.setSpacing(10);
        hb4.getChildren().add(tavT);
        hb4.getChildren().add(L4);

        hb5.setSpacing(10);
        hb5.getChildren().add(vafT);
        hb5.getChildren().add(L5);

        hb6.setSpacing(10);
        hb6.getChildren().add(dadT);
        hb6.getChildren().add(L6);

        hb7.setSpacing(10);
        hb7.getChildren().add(momT);
        hb7.getChildren().add(L7);

        hb8.setSpacing(10);
        hb8.getChildren().add(partnerT);
        hb8.getChildren().add(L8);

        hb9.setSpacing(25.0);
        hb9.setAlignment(Pos.CENTER);
        hb9.getChildren().add(isFemale);
        hb9.getChildren().add(isMale);
        isMale.setSelected(true);
        HBox.setMargin(save, new Insets(0, 0, 0, 85.0));
        save.setOnAction(e ->  saveClicked() );
        hb9.getChildren().add(save);

        isFemale.setToggleGroup(type);
        isMale.setToggleGroup(type);

        addRoot.getChildren().add(hb1);
        addRoot.getChildren().add(hb2);
        addRoot.getChildren().add(hb3);
        addRoot.getChildren().add(hb4);
        addRoot.getChildren().add(hb5);
        addRoot.getChildren().add(hb6);
        addRoot.getChildren().add(hb7);
        addRoot.getChildren().add(hb8);
        addRoot.getChildren().add(hb9);


        AnchorPane.setBottomAnchor(searchRoot, 0.0);
        AnchorPane.setTopAnchor(searchRoot, 0.0);
        AnchorPane.setRightAnchor(searchRoot, 0.0);
        AnchorPane.setLeftAnchor(searchRoot, 0.0);
        searchRoot.setSpacing(25.0);

        isName.setTextFill(Color.RED);
        isID.setTextFill(Color.RED);
        searchB.setTextFill(Color.RED);
        R1.setTextFill(Color.RED);
        R2.setTextFill(Color.RED);
        R3.setTextFill(Color.RED);
        R4.setTextFill(Color.RED);
        R5.setTextFill(Color.RED);
        R6.setTextFill(Color.RED);
        R7.setTextFill(Color.RED);
        R8.setTextFill(Color.RED);
        R9.setTextFill(Color.RED);
        R10.setTextFill(Color.RED);
        R11.setTextFill(Color.RED);
        R12.setTextFill(Color.RED);
        R13.setTextFill(Color.RED);
        R14.setTextFill(Color.RED);
        R15.setTextFill(Color.RED);

        searchVBox.setAlignment(Pos.CENTER);
        searchVBox.setPrefWidth(250.0);
        searchVBox.setSpacing(10.0);

        isID.setToggleGroup(searchT);
        isName.setToggleGroup(searchT);
        VBox.setMargin(searchB, new Insets(45.0, 0.0, 0.0, 0.0));
        searchVBox.getChildren().add(searchIDT);
        searchVBox.getChildren().add(isID);
        searchVBox.getChildren().add(searchNameT);
        searchVBox.getChildren().add(isName);
        searchVBox.getChildren().add(searchB);
        searchB.setOnAction(e -> searching());

        ratioVBox.setSpacing(5.0);
        ratioVBox.setAlignment(Pos.CENTER_LEFT);
        R1.setToggleGroup(ratioT);
        R2.setToggleGroup(ratioT);
        R3.setToggleGroup(ratioT);
        R4.setToggleGroup(ratioT);
        R5.setToggleGroup(ratioT);
        R6.setToggleGroup(ratioT);
        R7.setToggleGroup(ratioT);
        R8.setToggleGroup(ratioT);
        R9.setToggleGroup(ratioT);
        R10.setToggleGroup(ratioT);
        R11.setToggleGroup(ratioT);
        R12.setToggleGroup(ratioT);
        R13.setToggleGroup(ratioT);
        R14.setToggleGroup(ratioT);
        R15.setToggleGroup(ratioT);
        R1.setSelected(true);
        searchT.selectedToggleProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue == isID) {
                searchIDT.setDisable(false);
                searchNameT.setDisable(true);
            } else {
                searchNameT.setDisable(false);
                searchIDT.setDisable(true);
            }
        }));
        isID.setSelected(true);
        ratioVBox.getChildren().add(R1);
        ratioVBox.getChildren().add(R2);
        ratioVBox.getChildren().add(R3);
        ratioVBox.getChildren().add(R4);
        ratioVBox.getChildren().add(R5);
        ratioVBox.getChildren().add(R6);
        ratioVBox.getChildren().add(R7);
        ratioVBox.getChildren().add(R8);
        ratioVBox.getChildren().add(R9);
        ratioVBox.getChildren().add(R10);
        ratioVBox.getChildren().add(R11);
        ratioVBox.getChildren().add(R12);
        ratioVBox.getChildren().add(R13);
        ratioVBox.getChildren().add(R14);
        ratioVBox.getChildren().add(R15);

        searchRoot.getChildren().add(searchVBox);
        searchRoot.getChildren().add(ratioVBox);


        AnchorPane.setBottomAnchor(resultText, 0.0);
        AnchorPane.setRightAnchor(resultText, 0.0);
        AnchorPane.setTopAnchor(resultText, 0.0);
        AnchorPane.setLeftAnchor(resultText, 0.0);
        resultText.setEditable(false);
        resultText.setWrapText(true);
        resultText.setPrefHeight(380.0);
        resultText.setPrefWidth(360.0);


        AnchorPane.setLeftAnchor(waitPane, 0.0);
        AnchorPane.setTopAnchor(waitPane, 0.0);
        AnchorPane.setRightAnchor(waitPane, 0.0);
        AnchorPane.setBottomAnchor(waitPane, 0.0);
        waitPane.setPrefSize(200, 200);
        BorderPane.setAlignment(waitingL, Pos.CENTER);
        waitingL.setTextFill(Color.RED);
        waitPane.setCenter(waitingL);

    }


    public void initialize() {
        add.setOnAction(e -> {
            root.getChildren().clear();
            root.getChildren().add(addRoot);
        });
        babies.setOnAction(e -> {
            waiting();
            resultText.clear();
            resultText.setText("بیشترین فرزند برای: \n" + CounterPerson.getBigBabiesOfParent().toString());
            unWaiting();
            root.getChildren().add(resultText);
        });
        search.setOnAction(e -> {
            root.getChildren().clear();
            root.getChildren().add(searchRoot);
        });
        nonRatio.setOnAction(e -> {
            waiting();
            resultText.clear();
            resultText.setText("زوج های بدون نسبت فامیلی: \n" + CounterPerson.nonRatioPartners().toString());
            unWaiting();
            root.getChildren().add(resultText);
        });
        graphInf.setOnAction(e -> {
            root.getChildren().clear();
            resultText.clear();
            resultText.setText("درباره گراف و پروژه... \n\n\n" + about);
            root.getChildren().add(resultText);
        });
        getter.setOnAction(e -> {
            TextInputDialog textInputDialog = new TextInputDialog();
            textInputDialog.setHeaderText("لطفا ID Person1 را وارد نمایید...");
            textInputDialog.setContentText("ID Person1:");
            String s1 = textInputDialog.showAndWait().orElse(null);
            textInputDialog.setHeaderText("لطفا ID Person2 را وارد نمایید...");
            textInputDialog.setContentText("ID Person2:");
            String s2 = textInputDialog.showAndWait().orElse(null);
            if (s1 != null && s2 != null){
                resultText.clear();
                waiting();
                try{
                    int n1 = Integer.parseInt(s1);
                    int n2 = Integer.parseInt(s2);
                    resultText.setText("\n" +
                            CounterPerson.findPersonByID(n2).getName() +
                            "  " +
                            CounterPerson.getRatio(n1, n2) +
                            "  " +
                            CounterPerson.findPersonByID(n1).getName() +
                            "  " +
                            "است..."  );
                    unWaiting();
                    root.getChildren().add(resultText);
                } catch (Exception e1){
                    dialog(Alert.AlertType.ERROR, "خطا",
                            "مقادیر نامعتبر است!",
                            "لطفا مقادیر را اصلاح نمایید...");
                    unWaiting();
                }
            }
        });

        waiting();
        root.getChildren().clear();
        root.getChildren().add(addRoot);
        listView.refresh();
    }

    private void saveClicked(){
        if (IDT.getText().length() != 0
                & firstNameT.getText().length() != 0
                & lastNameT.getText().length() != 0
                & tavT.getText().length() != 0
                & dadT.getText().length() != 0
                & momT.getText().length() != 0
                & type.getSelectedToggle() != null){
                try {
                    String[] s = partnerT.getText().split(",");
                    if (s[s.length - 1].length() == 0){s[s.length - 1] = "-1";}
                    int[] perts = new int[s.length];
                    boolean bool = false;
                    try {
                        for (int c = 0 ; c < s.length ; c++) perts[c] = Integer.parseInt(s[c]);
                    } catch (Exception e){
                        if (vafT.getText().length() == 0){
                            dialog(Alert.AlertType.INFORMATION, "تایید", "کاربر به لیست اضافه شد...",
                                    new Person(Integer.parseInt(IDT.getText()),
                                            firstNameT.getText(),
                                            lastNameT.getText(),
                                            type.getSelectedToggle() == isFemale ? PersonType.FEMALE : PersonType.MALE,
                                            tavT.getText(),
                                            Integer.parseInt(dadT.getText()),
                                            Integer.parseInt(momT.getText()))
                                            .toString());
                        }
                        else { dialog(Alert.AlertType.INFORMATION, "تایید", "کاربر به لیست اضافه شد...",
                                new Person(Integer.parseInt(IDT.getText()),
                                        firstNameT.getText(),
                                        lastNameT.getText(),
                                        type.getSelectedToggle() == isFemale ? PersonType.FEMALE : PersonType.MALE,
                                        tavT.getText(),
                                        vafT.getText(),
                                        Integer.parseInt(dadT.getText()),
                                        Integer.parseInt(momT.getText()))
                                        .toString());
                        }
                        bool = true;
                    }
                    if (vafT.getText().length() == 0 && !bool){
                        dialog(Alert.AlertType.INFORMATION, "تایید", "کاربر به لیست اضافه شد...",
                                new Person(Integer.parseInt(IDT.getText()),
                                        firstNameT.getText(),
                                        lastNameT.getText(),
                                        type.getSelectedToggle() == isFemale ? PersonType.FEMALE : PersonType.MALE,
                                        tavT.getText(),
                                        Integer.parseInt(dadT.getText()),
                                        Integer.parseInt(momT.getText()),
                                        perts)
                                        .toString());
                    }
                    else if (!bool){
                        dialog(Alert.AlertType.INFORMATION, "تایید", "کاربر به لیست اضافه شد...",
                            new Person(Integer.parseInt(IDT.getText()),
                                    firstNameT.getText(),
                                    lastNameT.getText(),
                                    type.getSelectedToggle() == isFemale ? PersonType.FEMALE : PersonType.MALE,
                                    tavT.getText(),
                                    vafT.getText(),
                                    Integer.parseInt(dadT.getText()),
                                    Integer.parseInt(momT.getText()),
                                    perts)
                                    .toString());
                    }
                    listView.setItems(CounterPerson.getObservableList());
                    isStarted();
                }
                catch (Exception e){
                    dialog(Alert.AlertType.ERROR,"خطا در ورودی اطلاعات",
                            "وروردی ها را چک کنید!",
                            "باید ورودی ها درست باشند...");
                }
        }
        else dialog(Alert.AlertType.ERROR,"خطا در ورودی اطلاعات",
                "وروردی ها را چک کنید!",
                "باید ورودی های مهم را حتما پر نمایید...");
    }
    private void searching(){
        try {
            RatioType type;
            RadioButton b = (RadioButton) ratioT.getSelectedToggle();
            if (R1 == b) {
                type = RatioType.PEDAR;
            } else if (R2 == b) {
                type = RatioType.MADAR;
            } else if (R3 == b) {
                type = RatioType.BARADAR;
            } else if (R4 == b) {
                type = RatioType.KHAHAR;
            } else if (R5 == b) {
                type = RatioType.PESAR;
            } else if (R6 == b) {
                type = RatioType.DOKHTAR;
            } else if (R7 == b) {
                type = RatioType.ZAN;
            } else if (R8 == b) {
                type = RatioType.SHOUHAR;
            } else if (R9 == b) {
                type = RatioType.DAYE;
            } else if (R10 == b) {
                type = RatioType.KHALE;
            } else if (R11 == b) {
                type = RatioType.AMOU;
            } else if (R12 == b) {
                type = RatioType.AMME;
            } else if (R13 == b) {
                type = RatioType.BAJENAGH;
            } else if (R14 == b) {
                type = RatioType.JARY;
            } else if (R15 == b) {
                type = RatioType.HAWO;
            } else throw new IllegalArgumentException();

            if (searchT.getSelectedToggle() == isID){
                waiting();
                resultText.clear();
                resultText.setText(
                        CounterPerson.getPersonsXRatio(Integer.parseInt(searchIDT.getText().trim()), type).toString()
                ); //.
                unWaiting();
                root.getChildren().add(resultText);
            } else {
                waiting();
                resultText.clear();
                resultText.setText(CounterPerson.getPersonsXRatio(searchNameT.getText().trim(), type).toString());
                unWaiting();
                root.getChildren().add(resultText);
            }
        }catch (Exception e){
            dialog(Alert.AlertType.ERROR,
                    "خطا", "ورودی نامعتبر...", "لطفا ورودی هارا چک نمایید...");
            unWaiting();
            root.getChildren().clear();
            root.getChildren().add(searchRoot);
        }
    }

    private void waiting() {
        root.getChildren().clear();
        root.getChildren().add(waitPane);
        add.setDisable(true);
        babies.setDisable(true);
        search.setDisable(true);
        getter.setDisable(true);
        nonRatio.setDisable(true);
        graphInf.setDisable(true);
    }
    private void unWaiting(){
        root.getChildren().clear();
        add.setDisable(false);
        babies.setDisable(false);
        search.setDisable(false);
        getter.setDisable(false);
        nonRatio.setDisable(false);
        graphInf.setDisable(false);
    }
    private void isStarted(){
        if (listView.getItems() != null && listView.getItems().size() != 0){
            add.setDisable(false);
            babies.setDisable(false);
            search.setDisable(false);
            getter.setDisable(false);
            nonRatio.setDisable(false);
            graphInf.setDisable(false);
        }
    }
    private void dialog(Alert.AlertType type, String title, String header, String massage){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(massage);
        alert.showAndWait();
    }
}
