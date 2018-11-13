package com.viettel.asset.rest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;

import com.viettel.asset.bo.CatAssetCode;
import com.viettel.asset.business.CatAssetCodeBusiness;
import com.viettel.asset.business.LongTermAssetBusiness;
import com.viettel.asset.business.MerHandOverInfoBusiness;
import com.viettel.asset.dto.AutocompleteSearchDto;
import com.viettel.asset.dto.BaseSearchDto;
import com.viettel.asset.dto.CatAssetCodeDto;
import com.viettel.asset.dto.LongTermAssetCostDto;
import com.viettel.asset.dto.LongTermAssetDto;
import com.viettel.asset.dto.MerEntityDto;
import com.viettel.asset.dto.search.AssetHandOverSearchDto;
import com.viettel.asset.dto.service.PagingDto;
import com.viettel.asset.filter.session.UserSession;
import com.viettel.erp.rest.FileServiceImpl;
import com.viettel.erp.utils.Folder;
import com.viettel.ktts2.common.BusinessException;

import net.sf.jxls.transformer.XLSTransformer;

@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.MULTIPART_FORM_DATA })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.MULTIPART_FORM_DATA })
public class LongTermAssetRsRService extends AbstractRsService {

	@Inject
	private UserSession userSession;
	@Autowired
	LongTermAssetBusiness longTermAssetBusiness;
	@Autowired
	CatAssetCodeBusiness catAssetCodeBusiness;
	@Autowired
	MerHandOverInfoBusiness merHandOverInfoBusiness;
	// @Value("${folder_upload2}")
	private String folderUpload;
	static Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

	/**
	 * Lay lotaIndex tiep theo
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/getNewLotaIndex/")
	public Response getNewLotaIndex(LongTermAssetDto obj) throws Exception {
		return Response.ok(longTermAssetBusiness.getCaacFullCodeTemp(obj.getCatAssetCodeId())).build();
	}

	/*
	 * tim kiem autocomplete tai san
	 */
	@POST
	@Path("/getForAutoComplete/")
	public Response getForAutoComplete(LongTermAssetDto obj) throws Exception {
		return Response.ok(longTermAssetBusiness.getForAutoComplete(obj)).build();
	}

	/*
	 * Lay cong trinh cho autocomplete Hien tai chua su dung o module tscd
	 */
	@Deprecated
	@POST
	@Path("/getConstrConstructionsForAutoComplete/")
	public Response getConstrConstructionsAutoComplete(AutocompleteSearchDto query) throws Exception {
		return Response.ok(longTermAssetBusiness.getConstrConstructionForAutoComplete(query)).build();
	}

	/*
	 * lay tram cho autocomplete hien tai chua su dung o module tscd
	 */
	@Deprecated
	@POST
	@Path("/getStationForAutoComplete/")
	public Response getStationForAutoComplete(AutocompleteSearchDto query) throws Exception {
		return Response.ok(longTermAssetBusiness.getStationForAutoComplete(query)).build();
	}

	@POST
	@Path("/getSysGroupForAutoComplete/")
	public Response getSysGroupForAutoComplete(AutocompleteSearchDto query) throws Exception {
		return Response.ok(longTermAssetBusiness.getSysGroupForAutoComplete(query)).build();
	}

	/**
	 * Tim kiem tai san khong phan trang (co the phuc vu su dung export excel
	 * sau
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	@POST
	@Path("/search/")
	public Response search(LongTermAssetDto obj) throws Exception {
		return Response.ok(longTermAssetBusiness.getAllAsset(obj)).build();
	}

	/**
	 * Tìm kiếm tài sản
	 * 
	 * @param pagingInfo
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/searchLtaPaginate/")
	public Response searchLtaPaginate(@BeanParam PagingDto pagingInfo, LongTermAssetDto obj) throws Exception {
		return Response.ok(longTermAssetBusiness.searchLtaPaginate(obj, pagingInfo)).build();
	}

	/**
	 * Export danh sach tai san
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */

	@Deprecated
	@POST
	@Path("/exportExcel/")
	public Response exportExcel(LongTermAssetDto obj) throws Exception {
		Map beans = new HashMap();
		List<LongTermAssetDto> list = longTermAssetBusiness.getAllAsset(obj);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setRownum(i + 1);
			if (list.get(i).getLotaType() != null && list.get(i).getLotaType() == 1l) {
				list.get(i).setLotaTypeText("Tài sản cố định");
			} else if (list.get(i).getLotaType() != null && list.get(i).getLotaType() == 2l) {
				list.get(i).setLotaTypeText("Công cụ dụng cụ");
			}

			if (list.get(i).getIsSentErp() != null && list.get(i).getIsSentErp() == 0l) {
				list.get(i).setIsSentErpText("Chưa đồng bộ");
			} else if (list.get(i).getIsSentErp() != null && list.get(i).getIsSentErp() == 1l) {
				list.get(i).setIsSentErpText("Đã đồng bộ");
			}

			if (list.get(i).getLotaStatus() != null && list.get(i).getLotaStatus() == 0l) {
				list.get(i).setLotaStatusText("Tạm tăng");
			} else if (list.get(i).getLotaStatus() != null && list.get(i).getLotaStatus() == 1l) {
				list.get(i).setLotaStatusText("Đã quyết toán");
			}

			if (list.get(i).getDepreciationStartDate() != null) {
				DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				String datetext = format.format(list.get(i).getDepreciationStartDate());
				list.get(i).setStringDate(datetext);
			}
		}

		beans.put("assets", list);
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../" + "doc-template").getPath();
		InputStream is = new BufferedInputStream(new FileInputStream(filePath + "ExportAsset.xlsx"));
		XLSTransformer transformer = new XLSTransformer();
		Workbook resultWorkbook = transformer.transformXLS(is, beans);
		is.close();
		saveWorkbook(resultWorkbook, folderUpload + "/ExportAsset.xlsx");

		return Response.ok(Collections.singletonMap("fileName", "ExportAsset.xlsx")).build();
	}

	private void saveWorkbook(Workbook resultWorkbook, String fileName) throws IOException {
		OutputStream os = new BufferedOutputStream(new FileOutputStream(fileName));
		resultWorkbook.write(os);
		os.flush();
		os.close();
	}

	/**
	 * Gửi tài chính
	 * 
	 * @param id:
	 *            id long_term_asset
	 * @return response ok -> thành công
	 * @throws Exception
	 */
	@GET
	@Path("/send/{id}")
	public Response send(@PathParam("id") Long id) throws Exception {
		longTermAssetBusiness.sendToErp(id);
		return Response.ok().build();
	}

	/**
	 * Xóa tài sản khi chưa gửi sang tài chính thành công
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/remove/{id}/")
	public Response remove(@PathParam("id") Long id) throws Exception {
		longTermAssetBusiness.delete(id);
		return Response.ok().build();
	}

	@Deprecated
	@POST
	@Path("/getMerEntity/")
	public Response getMerEntity(MerEntityDto obj) throws Exception {
		return Response.ok(longTermAssetBusiness.getMerEntity(obj)).build();
	}

	@Deprecated
	@POST
	@Path("/getConstructionAcceptance/")
	public Response getConstructionAcceptance(MerEntityDto obj) throws Exception {
		return Response.ok(longTermAssetBusiness.getConstructionAcceptance(obj)).build();
	}

	/*
	 * Lấy thông tin biên bản bàn giao không qua xây láp cho việc hình thành tài
	 * sản cố định
	 */
	@GET
	@Path("/getConstructionAcceptanceById/{id}")
	public Response getConstructionAcceptanceById(@PathParam(value = "id") Long id) throws Exception {
		return Response.ok(longTermAssetBusiness.getConstructionAcceptanceById(id)).build();
	}

	@GET
	@Path("/getFolder")
	public Response getFolder() throws Exception {
		return Response.ok().entity(java.util.Collections.singletonMap("folder", folderUpload)).build();
	}

	@POST
	@Path("/add/")
	public Response addNew(LongTermAssetDto obj) throws Exception {
		return Response.ok(longTermAssetBusiness.addNew(obj)).build();
	}

	@POST
	@Path("/update/put/")
	public Response update(LongTermAssetDto obj) throws Exception {
		return Response.ok(longTermAssetBusiness.update(obj)).build();
	}

	@GET
	@Path("/getSerial/{id}")
	public Response getSerial(@PathParam("id") Long id) throws Exception {
		return Response.ok(longTermAssetBusiness.getSerial(id)).build();
	}

	@GET
	@Path("/getNoneSerial/{id}")
	public Response getNoneSerial(@PathParam("id") Long id) throws Exception {
		return Response.ok(longTermAssetBusiness.getNoneSerial(id)).build();
	}

	@GET
	@Path("/getDistri/{id}")
	public Response getDistri(@PathParam("id") Long id) throws Exception {
		return Response.ok(longTermAssetBusiness.getDistri(id)).build();
	}

	/*
	 * Tìm kiếm biên bản bàn giao (cả qua xây lắp lần ko qua xây lắp
	 */
	@POST
	@Path("/searchHandOver/")
	public Response searchHandover(AssetHandOverSearchDto handOverDto) throws Exception {
		return Response.ok(longTermAssetBusiness.searchHandover(handOverDto)).build();
	}

	/*
	 * Tìm kiếm biên bản bàn giao qua xây lắp
	 */
	@POST
	@Path("/searchHandOverByConstruction/")
	public Response searchHandOverByConstruction(AssetHandOverSearchDto handOverDto) throws Exception {
		return Response.ok(longTermAssetBusiness.searchHandoverByConstruction(handOverDto)).build();
	}

	/*
	 * Tìm kiếm biên bản bàn giao không qua xây lắp
	 */
	@POST
	@Path("/searchHandOverNotByConstruction/")
	public Response searchHandOverNotByConstruction(AssetHandOverSearchDto handOverDto) throws Exception {
		handOverDto.setUserSession(getUserSession());
		return Response.ok(longTermAssetBusiness.searchHandoverNotByConstruction(handOverDto)).build();
	}

	@POST
	@Path("/searchAssetCost/")
	public Response searchAssetCost(LongTermAssetCostDto obj) throws Exception {
		return Response.ok(longTermAssetBusiness.searchAssetCost(obj)).build();
	}

	@POST
	@Path("/getDetail")
	public Response getDetailAsset(LongTermAssetDto obj) throws Exception {
		return Response.ok(longTermAssetBusiness.getDetailAsset(obj.getLongTermAssetId())).build();
	}

	/// hanhls1 _lay du lieu cho merHandOver
	// @GET
	// @Path("/getMerhandOverToCreateLTAById/{id}")
	// public Response getMerhandOverToCreateLTAById(@PathParam(value="id") Long
	// id) throws Exception {
	// return
	// Response.ok(longTermAssetBusiness.getMerhandOverToCreateLTAById(id)).build();
	// }

	// Hanhls1 20170411: kiem tra ma tai san co thuoc loai bat buoc hinh thanh
	// tai san kong
	@GET
	@Path("/isRequiredToBeLTA")
	public Response isRequiredToBeLTA(@QueryParam("caacLevel") Long caacLevel,
			@QueryParam("catAssetCodeId") Long catAssetCodeId) throws Exception {
		return Response.ok(catAssetCodeBusiness.isRequiredToBeLTA(caacLevel, catAssetCodeId)).build();
	}

	/*
	 * Tìm kiếm biên bản bàn giao nâng cấp trạm
	 */
	@POST
	@Path("/searchHandOverUpgradeConstr/")
	public Response searchHandOverUpgradeConstr(AssetHandOverSearchDto handOverDto) throws Exception {
		handOverDto.setUserSession(getUserSession());
		return Response.ok(longTermAssetBusiness.searchHandOverUpgradeConstructionPaginate(handOverDto)).build();
	}

	/**
	 * Lấy thông tin biên bản bàn giao, kèm thông tin tài sản cố định
	 * 
	 * @param handOverId
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/loadMerHandOverNotByConstrForUpgrade/")
	public Response loadMerHandOverNotByConstrForUpgrade(Long handOverId) throws Exception {
		return Response.ok(merHandOverInfoBusiness.loadMerHandOverNotByConstrForUpgrade(handOverId)).build();
	};

	/**
	 * Hủy nâng cấp tài sản không qua xây lắp
	 * 
	 * @param longTermAssetId
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/cancelUpgradeLta/")
	public Response cancelUpgradeNotByConstr(LongTermAssetDto obj) throws Exception {
		UserSession userSession = getUserSession();
		return Response.ok(longTermAssetBusiness.cancelUpgradeByConstr(obj, userSession)).build();
	};

	/**
	 * Upgrade Ts
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/upgradeLta/")
	public Response upgradeLta(LongTermAssetDto obj) throws Exception {
		UserSession s = getUserSession();
		return Response.ok(longTermAssetBusiness.upgradeLta(obj, s)).build();
	}

	/**
	 * Autocomplete tìm kiếm tài sản
	 * 
	 * @param autoSearchForm
	 * @return
	 */
	@POST
	@Path("/autocompleteConstrFromOfferSlip/")
	public Response autocompleteConstrFromOfferSlip(AutocompleteSearchDto autoSearchForm) {
		return Response.ok(longTermAssetBusiness.autocompleteConstrFromOfferSlip(autoSearchForm)).build();
	}

	/**
	 * Tạo mới
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/createLtaFromOfferSlip/")
	public Response createLtaFromOfferSlip(LongTermAssetDto obj) throws Exception {
		return Response.ok(longTermAssetBusiness.createLtaFromOfferSlip(obj)).build();
	}

	/**
	 * Update
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/updateLtaFromOfferSlip/")
	public Response updateLtaFromOfferSlip(LongTermAssetDto obj) throws Exception {
		return Response.ok(longTermAssetBusiness.updateLtaFromOfferSlip(obj)).build();
	}
	
	@POST
	@Path("/getLtaAttachDetail/")
	public Response getLtaAttachDetail(LongTermAssetCostDto obj) throws Exception {
		return Response.ok(longTermAssetBusiness.getLtaAttachDetail(obj)).build();
	}

}
