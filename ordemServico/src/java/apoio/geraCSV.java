/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Douglas
 */
public class geraCSV {

    public void gerarCSV(String sql, HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        java.sql.ResultSet myResultSet = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
        // aqui salva o csv na pasta
        File file = new File("\\relatorios\\exporta.csv");
        System.out.println("Diretorio "+file.getPath());
        file.delete();
        FileWriter fw = new FileWriter("C:\\Users\\Douglas\\Downloads\\PROGRAMAÇÃO PARA INTERNET\\01 - PROGRAMAÇÃO PARA INTERNET\\ordemServico\\src\\java\\relatorios\\osStatus.csv", true);
        BufferedWriter bw = new BufferedWriter(fw);

        CSVWriter writer = new CSVWriter(bw, ';');
        Boolean includeHeaders = true;
        writer.writeAll(myResultSet, includeHeaders);
        writer.close();

    }

    public void downloadCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {

        File arquivo = new File("/exporta/exporta.csv");
        //aki le , pq tem endereços diferentes
        int tamanho = (int) arquivo.length();
        response.setContentType("text/csv");
        response.setContentLength(tamanho);
        response.setHeader("Content-Disposition", "attachment; filename=exporta.csv");

        OutputStream output = response.getOutputStream();
        Files.copy(arquivo.toPath(), output);

    }
}
