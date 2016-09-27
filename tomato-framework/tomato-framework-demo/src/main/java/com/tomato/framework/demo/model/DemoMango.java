package com.tomato.framework.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.tomato.framework.rest.model.BaseModel;

@Data
@EqualsAndHashCode(callSuper = false)
public class DemoMango extends BaseModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6693491989945842988L;

	private Integer id;

	private String name;

}
