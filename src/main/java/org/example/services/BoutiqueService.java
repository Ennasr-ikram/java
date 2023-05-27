package org.example.services;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.dao.BoutiqueDao;
import org.example.dao.Imp.BoutiqueDaoImpl;
import org.example.entities.Boutique;

import java.io.*;
import java.util.List;

public class BoutiqueService {
    private BoutiqueDao boutiqueDao = new BoutiqueDaoImpl();
    public List<Boutique> findAll() {
        return boutiqueDao.findAll();
    }
    public void save(Boutique boutique) {
        boutiqueDao.insert(boutique);
    }
    public void update(Boutique boutique) {
        boutiqueDao.update(boutique);
    }
    public Boutique findByID(int id) { return boutiqueDao.findById(id);}

    public void remove(Integer id) {
        boutiqueDao.deleteById(id);
    }

    public void writeXLSX(String path) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Boutique Info");

        XSSFRow headerRow = spreadsheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("adress");
        headerRow.createCell(3).setCellValue("nbvend");
        headerRow.createCell(4).setCellValue("Tel");
        headerRow.createCell(5).setCellValue("description");

        int rowNumber=1;
        for (Boutique boutique : boutiqueDao.findAll()){
            XSSFRow row = spreadsheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(boutique.getId());
            row.createCell(1).setCellValue(boutique.getnom());
            row.createCell(2).setCellValue(boutique.getadress());
            row.createCell(3).setCellValue(boutique.getnbvend());
            row.createCell(4).setCellValue(boutique.gettel());
            row.createCell(5).setCellValue(boutique.getdescription());
        }

        try (FileOutputStream out = new FileOutputStream(new File(path))) {
            workbook.write(out);
            System.out.println("Stades Data Exported Successfully To "+ path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readXLSX (String file) {

        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int i=0;
            for (Row row : sheet) {
                if (i != 0)
                {
                    Boutique boutique = new Boutique();
                    boutique.setId((int) row.getCell(0).getNumericCellValue());
                    boutique.setnom(row.getCell(1).getStringCellValue());
                    boutique.setadress(row.getCell(2).getStringCellValue());
                    boutique.setnbvend(Integer.parseInt(row.getCell(2).getStringCellValue()));
                    boutique.settel(row.getCell(2).getStringCellValue());
                    boutique.setdescription(row.getCell(2).getStringCellValue());

                    if (this.findByID(boutique.getId()) instanceof Boutique)
                        this.update(boutique);
                    else
                        this.save(boutique);
                }
                i=1;
            }
            workbook.close();
            inputStream.close();
            System.out.println("Boutique Data Imported Successfully From "+ file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}