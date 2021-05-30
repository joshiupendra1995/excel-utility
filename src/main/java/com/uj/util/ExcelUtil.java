package com.uj.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.uj.common.ExcelCellType;
import com.uj.model.ExcelCell;

/**
 * CreatedBy Upendra Joshi
 */
public class ExcelUtil {

	private ExcelUtil() {
	}

	public static byte[] createFile(List<String> fileHeader, List<List<ExcelCell>> fileData) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("sheet");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		// set header data
		if (fileHeader != null && !fileHeader.isEmpty()) {
			setFileHeader(fileHeader, sheet, workbook);
		}

		// Set body data
		int rownum = 1;
		for (List<ExcelCell> excelData : fileData) {
			XSSFRow rowData = sheet.createRow(rownum++);
			int cnt = 0;
			for (ExcelCell cellData : excelData) {
				XSSFCell cell = rowData.createCell(cnt++);
				try {
					if (cellData.getExcelCellType() == ExcelCellType.NUMERIC) {
						XSSFCellStyle numericCellStyle = workbook.createCellStyle();
						numericCellStyle.setDataFormat(workbook.createDataFormat().getFormat(cellData.getFormat()));
						cell.setCellStyle(numericCellStyle);
						cell.setCellValue(NumberUtil.createDouble((String) cellData.getValue()));
					} else if (cellData.getExcelCellType() == ExcelCellType.STRING) {
						cell.setCellValue((String) cellData.getValue());
					}
				} catch (Exception e) {
					cell.setCellValue((String) cellData.getValue());
				}

			}

		}

		// Auto size the column widths
		for (int columnIndex = 0; columnIndex < rownum; columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}

		workbook.write(bos);
		return bos.toByteArray();

	}

	private static void setFileHeader(List<String> fileHeader, XSSFSheet sheet, XSSFWorkbook workbook) {
		// Bold font boldCellStyle
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		XSSFCellStyle boldCellStyle = workbook.createCellStyle();
		boldCellStyle.setFont(font);

		XSSFRow rowHeader = sheet.createRow(0);
		int cellnum = 0;
		for (String h : fileHeader) {
			XSSFCell cell = rowHeader.createCell(cellnum++);
			cell.setCellValue(h);
			cell.setCellStyle(boldCellStyle);
		}

	}

}
