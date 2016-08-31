package CsvExport;

import model.PartialLocation;
import org.supercsv.io.dozer.CsvDozerBeanWriter;
import org.supercsv.io.dozer.ICsvDozerBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by k.benhmida on 28/08/2016.
 */
public class CsvWriter {

    private final Class beansClass;
    private final String[] fields;

    public CsvWriter(Class beansClass, String[] fields){
        this.beansClass = beansClass;
        this.fields = fields;
    }


    public void writeToCSV(Object[] objects, String filePath) throws CsvWritingException {
        ICsvDozerBeanWriter beanWriter = null;
        try {
            beanWriter = new CsvDozerBeanWriter(new FileWriter(filePath), CsvPreference.STANDARD_PREFERENCE);
            // configure the mapping from the fields to the CSV columns
            beanWriter.configureBeanMapping(beansClass, fields);
            for (Object object : objects) {beanWriter.write(object);}
        } catch (IOException e) {
            throw new CsvWritingException("An error occured while writing output csv file",e);
        } finally {
            if (beanWriter != null) {
                try {
                    beanWriter.close();
                } catch (IOException e) {
                    throw new CsvWritingException("An error occured while closing output csv file writer",e);
                }
            }
        }
    }

}
