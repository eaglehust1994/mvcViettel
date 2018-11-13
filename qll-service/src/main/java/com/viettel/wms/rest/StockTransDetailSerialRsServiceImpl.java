/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.wms.rest;

import com.viettel.wms.business.StockTransDetailSerialBusinessImpl;
import com.viettel.wms.dto.StockTransDetailSerialDTO;
import com.viettel.service.base.dto.DataListDTO;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author HungLQ9
 */
public class StockTransDetailSerialRsServiceImpl implements StockTransDetailSerialRsService {

	   // protected final Logger log = Logger.getLogger(UserRsService.class);
	    @Autowired
	    StockTransDetailSerialBusinessImpl stockTransDetailSerialBusinessImpl;

	    @Override
	    public Response getStockTransDetailSerial() {
	        List<StockTransDetailSerialDTO> ls = stockTransDetailSerialBusinessImpl.getAll();
	        if (ls == null) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	            DataListDTO data = new DataListDTO();
	            data.setData(ls);
	            data.setTotal(ls.size());
	            data.setSize(ls.size());
	            data.setStart(1);
	            return Response.ok(data).build();
	        }
	    }

	    @Override
	    public Response getStockTransDetailSerialById(Long id) {
	        StockTransDetailSerialDTO obj = (StockTransDetailSerialDTO) stockTransDetailSerialBusinessImpl.getOneById(id);
	        if (obj == null) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	            return Response.ok(obj).build();
	        }
	    }

	    @Override
	    public Response updateStockTransDetailSerial(StockTransDetailSerialDTO obj) {
	        Long id = stockTransDetailSerialBusinessImpl.update(obj);
	        if (id == 0l) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	            return Response.ok().build();
	        }

	    }

	    @Override
	    public Response addStockTransDetailSerial(StockTransDetailSerialDTO obj) {
	        Long id = stockTransDetailSerialBusinessImpl.save(obj);
	        if (id == 0l) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	            return Response.ok(Response.Status.CREATED).build();
	        }
	    }

	    @Override
	    public Response deleteStockTransDetailSerial(Long id) {
	        StockTransDetailSerialDTO obj = (StockTransDetailSerialDTO) stockTransDetailSerialBusinessImpl.getOneById(id);
	        if (obj == null) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	            stockTransDetailSerialBusinessImpl.delete(obj);
	            return Response.ok(Response.Status.NO_CONTENT).build();
	        }
	    }

		@Override
		public Response doSearchGoodsDetailForImportNote(StockTransDetailSerialDTO obj) {
			// TODO Auto-generated method stub
			DataListDTO data = stockTransDetailSerialBusinessImpl.doSearchGoodsDetailForImportNote(obj);
	        if (data == null) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	        	return Response.ok(data).build();
	        }
		}
		
		@Override
		public Response doSearchGoodsDetailSerial(StockTransDetailSerialDTO obj) {
			// TODO Auto-generated method stub
			DataListDTO data = stockTransDetailSerialBusinessImpl.doSearchGoodsDetailSerial(obj);
	        if (data == null) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	        	return Response.ok(data).build();
	        }
		}

		@Override
		public Response getGoodsInfoByCode(String code) {
			// TODO Auto-generated method stub
			List<StockTransDetailSerialDTO> lst = stockTransDetailSerialBusinessImpl.getGoodsInfoByCode(code);
	        if (lst == null) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	        	return Response.ok(lst).build();
	        }
		}

		@Override
		public Response doSearchGoodsDetailForExportNote() {
			// TODO Auto-generated method stub
			DataListDTO data = stockTransDetailSerialBusinessImpl.doSearchGoodsDetailForExportNote();
	        if (data == null) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	        	return Response.ok(data).build();
	        }
		}

		@Override
		public Response getGoodsDetail(StockTransDetailSerialDTO obj) {
			// TODO Auto-generated method stub
			List<StockTransDetailSerialDTO> lst = stockTransDetailSerialBusinessImpl.getGoodsDetail(obj);
	        if (lst == null) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	        	return Response.ok(lst).build();
	        }
		}

		/**
		 * Danh sach thong tin serial
		 */
		@Override
		public Response doSearchGoodsDetailForExportNote(StockTransDetailSerialDTO obj) {
			DataListDTO data = stockTransDetailSerialBusinessImpl.doSearchGoodsDetailForExportNote(obj);
	        if (data == null) {
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        } else {
	        	return Response.ok(data).build();
	        }
		}
		
	}
