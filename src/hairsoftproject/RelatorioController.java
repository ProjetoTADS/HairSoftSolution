
package hairsoftproject;
import com.itextpdf.text.BadElementException;
import hairsoftproject.DAO.ConexaoMySql;
import static hairsoftproject.DAO.ConexaoMySql.fecharConexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RelatorioController {
     Connection connection = hairsoftproject.DAO.ConexaoMySql.getConexaoMySql();
     private String path = System.getProperty("user.dir");//diretório da aplicação
     
     public void GerarRelatorio() throws SQLException, DocumentException, BadElementException, IOException{
         Statement Stmt = connection.createStatement();
            ResultSet myRs = Stmt.executeQuery("SELECT CL.NOME, DATE_FORMAT(AG.DATA_AGENDAMENTO, '%d-%m-%Y'), AG.SERVICO_DES, AG.PRECO"
                    + "                           FROM AGENDAMENTO AG,"
                    + "                                CLIENTE CL"
                    + "                          WHERE CL.CLIENTE_ID = AG.CLIENTE_ID"
                    + "                            AND IFNULL(CL.ATIVO, 'SIM') = 'SIM'");
           Document document = new Document();
           PdfPTable table = new PdfPTable(4);
           table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
           table.addCell("Nome");
           table.addCell("Data Agendamento");
           table.addCell("Descrição do serviço");
           table.addCell("Preço");
           table.setHeaderRows(1);
           PdfPCell[] cells = table.getRow(0).getCells();
           for(int j=0;j<cells.length;j++)
           {
              cells[j].setBackgroundColor(BaseColor.GRAY);
           }
           while(myRs.next())
           {
              table.addCell(myRs.getString(1));
              table.addCell(myRs.getString(2));
              table.addCell(myRs.getString(3));
              table.addCell(myRs.getString(4));
           }
           
           if(!CheckFileExist(FileName()))
           {
               CreateDocument(document,FileName(), table);
           }
           else
           {
               CreateDocument(document,FileName(), table);
           }
           
           
     }
     
     public void CreateDocument(Document doc ,String fileName,PdfPTable table) throws DocumentException, FileNotFoundException, BadElementException, IOException
    {
        PdfWriter.getInstance(doc, new FileOutputStream(path + "\\" + fileName));
        doc.open();
        Paragraph paragrafo = new Paragraph();
        paragrafo.add("Relatório de agendamentos");
        paragrafo.setAlignment(Element.ALIGN_CENTER);
        doc.add(paragrafo);
        doc.add(Chunk.NEWLINE);
        Font f=new Font(FontFamily.TIMES_ROMAN,10.0f,Font.BOLD,BaseColor.BLACK);
        Paragraph paragrafoEmissao = new Paragraph(GetDataAtual(),f);
        paragrafoEmissao.setAlignment(Element.ALIGN_CENTER);
        doc.add(paragrafoEmissao);
        doc.add(Chunk.NEWLINE);
        doc.add(table);
        doc.close();
        Desktop.getDesktop().open(new File(path + "\\" + fileName));
    }
     
    public String GetDataAtual()
    {
        LocalDate data = java.time.LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dataFormatada = data.format(format);
        return dataFormatada;
    }
    
    public String FileName()
    {
        return "relatorio_agendamentos_" + GetDataAtual() + ".pdf";
    }
     
    public boolean CheckFileExist(String fileName)
    {
        File f =  new File(path + "\\" + fileName);
        if(f.exists())
        {
            f.delete();//exclui o arquivo
            return true;
        }
        else
        {
            return false;
        }
    }
}
