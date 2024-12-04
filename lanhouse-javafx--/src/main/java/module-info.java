module com.projeto.lanhouse.lanhousejavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;  // Adiciona o acesso ao módulo Gson
    requires com.fasterxml.jackson.databind; // Para usar ObjectMapper
    requires com.fasterxml.jackson.core; // Necessário para manipulação de JSON


    exports com.projeto.lanhouse.lanhousejavafx;
    exports com.projeto.lanhouse.lanhousejavafx.controller to javafx.fxml;  // Exporta o pacote para javafx.fxml
    opens com.projeto.lanhouse.lanhousejavafx.controller to javafx.fxml;
    exports com.projeto.lanhouse.lanhousejavafx.model;    // Abre o pacote para javafx.fxml
}
