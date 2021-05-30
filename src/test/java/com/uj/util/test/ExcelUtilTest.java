package com.uj.util.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.uj.common.ExcelCellType;
import com.uj.model.ExcelCell;
import com.uj.util.ExcelUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class ExcelUtilTest {

	@Test
	public void testCreateFile() throws Exception {
		File outputFile = new File("src/test/resources/output/sample.xlsx");
		FileOutputStream fos = new FileOutputStream(outputFile);
		try {
			List<String> fileHeader = Arrays.asList("Id", "Name", "Salary");
			List<List<ExcelCell>> fileData = new ArrayList<List<ExcelCell>>();
			List<ExcelCell> record1 = Arrays.asList(new ExcelCell("1", ExcelCellType.NUMERIC, "#"),
					new ExcelCell("Upendra", ExcelCellType.STRING, null),
					new ExcelCell("5000.00", ExcelCellType.NUMERIC, "#,##0.00_);[Red](#,##0.00)"));
			List<ExcelCell> record2 = Arrays.asList(new ExcelCell("2", ExcelCellType.NUMERIC, "#"),
					new ExcelCell("UJ", ExcelCellType.STRING, null),
					new ExcelCell("-6000.00", ExcelCellType.NUMERIC, "#,##0.00_);[Red](#,##0.00)"));

			fileData.add(record1);
			fileData.add(record2);

			byte[] bytes = ExcelUtil.createFile(fileHeader, fileData);
			fos.write(bytes);
			
			assertTrue(outputFile.exists());
		} catch (Exception e) {
			throw e;
		} finally {
			fos.close();
		}
	}
}
