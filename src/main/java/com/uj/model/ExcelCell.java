package com.uj.model;

import com.uj.common.ExcelCellType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CreatedBy Upendra Joshi
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExcelCell {
	private Object value;
	private ExcelCellType excelCellType;
	private String format;

}
