package ua.com.csltd.dao.impl;

/**
 * Created by valeriy_solyanik
 * on 10.08.2016.
 */

import oracle.jdbc.OracleTypes;
import oracle.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.support.oracle.SqlArrayValue;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.AbstractSqlTypeValue;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import ua.com.csltd.beans.entity.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: makiyov
 * Date: 01.04.13
 * Time: 12:21
 */
@Repository
public class PdfOrderABSDAOImpl  {

	@Autowired
	private SimpleJdbcCall simpleJdbcCallB2;

	public List<PushResponse> push(final List<PdfOrderEntityBean> documentList, final BigDecimal branchId) throws SQLException {
		setStateIdReadyToSend(documentList);  // Проставка статуса 1000, если он 1106

/*
			SqlTypeValue sqlTypeValue = new SqlTypeArrayValue(documentList, "IFOBS_PDFORDEREXT_REC", new BeanPropertyArrayStructMapper(PdfOrderEntityBean.class) {
				@Override
				public void registerCustomEditor(BeanWrapper bw) {
					bw.registerCustomEditor(List.class, "pdfField_tab", new ArrayTypeEditor(new BeanPropertyArrayStructMapper(PdfOrderFieldEntityBean.class)));
					bw.registerCustomEditor(List.class, "pdffiles", new ArrayTypeEditor(new BeanPropertyArrayStructMapper(PdfFileEntity.class) {
						@Override
						public void registerCustomEditor(BeanWrapper bw) {
							bw.registerCustomEditor(List.class, "pdfgroups", new ArrayTypeEditor(new BeanPropertyArrayStructMapper(PdfGroupEntity.class)));
						}
					}));
				}
			});

			return simpleJdbcCallB2.withCatalogName("PKG_IFOBSGATE_DIRECT")
					.withFunctionName("f_pushPdfOrderExt")
					.declareParameters(returnCursor(PushResponse.class), new SqlParameter("in_PdfOrder", OracleTypes.ARRAY, "IFOBS_PDFORDER_TAB"))
					.executeFunction(List.class, sqlTypeValue);*/
/*


			List<Object[]> dataList = new ArrayList<>();
			for (PdfOrderEntityBean bean : documentList) {
				BLOB sourceImage = null;
				if (bean.getSourceImage() != null) {
					sourceImage = BLOB.createTemporary(simpleJdbcCallB2.getJdbcTemplate().getDataSource().getConnection(), false, BLOB.DURATION_SESSION);
					sourceImage.setBytes(1, bean.getSourceImage());
				}

				dataList.add(
						obj(
								bean.getId(),
								bean.getFieldCount(),
								bean.getStateId(),
								bean.getRejectComment(),
								bean.getReceiveDate(),
								bean.getCreateUserId(),
								bean.getLastModifiedUserId(),
								bean.getLastModifiedDate(),
								bean.getBranchId(),
								getFieldData(bean),
								bean.getUser1Login(),
								bean.getUser2Login(),
								bean.getAbsOrderId(),
								bean.getPdfFileId(),
								sourceImage,
								bean.getSourceImageSize(),
								bean.getDocumentDate(),
								bean.getProcess_errormsg(),
								bean.getStreamId(),
								bean.getSmsAuth(),
								bean.getSmsCodeHash(),
								bean.getSmsCodeCount(),
								bean.getSmsCodeExpireDate(),
								bean.getDigipassPassword(),
								getPdfFilesData(bean)
						)
				);
			}
*/


		SqlTypeValue array = new AbstractSqlTypeValue() {
			@Override
			protected Object createTypeValue(Connection connection, int i, String s) throws SQLException {

				StructDescriptor recDescr = StructDescriptor.createDescriptor("IFOBS_PDFORDEREXT_REC", connection);
				List<STRUCT> collection = new ArrayList<>();

				for (PdfOrderEntityBean bean : documentList) {
					BLOB sourceImage = null;
					if (bean.getSourceImage() != null) {
						sourceImage = BLOB.createTemporary(connection, false, BLOB.DURATION_SESSION);
						sourceImage.setBytes(1, bean.getSourceImage());
					}

					Object[] orders = obj(
							bean.getId(),
							bean.getFieldCount(),
							bean.getStateId(),
							bean.getRejectComment(),
							bean.getReceiveDate(),
							bean.getCreateUserId(),
							bean.getLastModifiedUserId(),
							bean.getLastModifiedDate(),
							bean.getBranchId(),
							getFieldData(bean),
							bean.getUser1Login(),
							bean.getUser2Login(),
							bean.getAbsOrderId(),
							bean.getPdfFileId(),
							sourceImage,
							bean.getSourceImageSize(),
							bean.getDocumentDate(),
							bean.getProcess_errormsg(),
							bean.getStreamId(),
							bean.getSmsAuth(),
							bean.getSmsCodeHash(),
							bean.getSmsCodeCount(),
							bean.getSmsCodeExpireDate(),
							bean.getDigipassPassword(),
							getPdfFilesData(bean)
					);

					STRUCT messageStruct = new STRUCT(recDescr, connection, orders);
					collection.add(messageStruct);
				}

				ArrayDescriptor tabDescriptor = ArrayDescriptor.createDescriptor("IFOBS_PDFORDEREXT_TAB", connection);
				return new ARRAY(tabDescriptor, connection, collection.toArray());
			}
		};

			return simpleJdbcCallB2.withCatalogName("PKG_IFOBSGATE_DIRECT")
					.withFunctionName("f_pushPdfOrderExt")
					.declareParameters(returnCursor(PushResponse.class), new SqlParameter("in_PdfOrder", OracleTypes.ARRAY, "IFOBS_PDFORDEREXT_TAB"))
					.executeFunction(List.class, array);
	}

	private Object[] getFieldData(PdfOrderEntityBean bean) {
		Object[] fieldData = new Object[0];
		if (!CollectionUtils.isEmpty(bean.getPdfField_tab())) {
			fieldData = new Object[bean.getPdfField_tab().size()];
			int i = 0;
			for (PdfOrderFieldEntityBean field : bean.getPdfField_tab()) {
				fieldData[i++] = obj(
						field.getId(),
						field.getName(),
						field.getValue(),
						field.getFieldNo()
				);
			}
		}
		return fieldData;
	}

	private Object[] getPdfFilesData(PdfOrderEntityBean bean) {
		Object[] pdfFilesData = new Object[0];
		if (!CollectionUtils.isEmpty(bean.getPdffiles())) {
			pdfFilesData = new Object[bean.getPdffiles().size()];
			int i = 0;
			for (PdfFileEntity entity : bean.getPdffiles()) {
				pdfFilesData[i++] = obj(
						entity.getId(),
						entity.getFileName(),
						entity.getFileSize(),
						entity.getPdfGroupId(),
						entity.getDescr(),
						entity.getIsInactive(),
						getPdfGroupsData(entity)
				);
			}
		}
		return pdfFilesData;
	}

	private Object[] getPdfGroupsData(PdfFileEntity bean) {
		Object[] pdfGroupsData = new Object[0];
		if (!CollectionUtils.isEmpty(bean.getPdfgroups())) {
			pdfGroupsData = new Object[bean.getPdfgroups().size()];
			int i = 0;
			for (PdfGroupEntity entity : bean.getPdfgroups()) {
				pdfGroupsData[i++] = obj(
						entity.getId(),
						entity.getName(),
						entity.getDescr()
						);
			}
		}
		return pdfGroupsData;
	}

	protected <T extends AbstractDocumentEntityBean> void setStateIdReadyToSend(List<T> dataInfoList) {
		for (T data : dataInfoList) {
			data.setStateId(new BigDecimal(1106).equals(data.getStateId()) ? new BigDecimal(1000) : data.getStateId());
		}
	}

	protected <T> SqlOutParameter returnCursor(Class<T> mappedClass) {
		return new SqlOutParameter("return", OracleTypes.CURSOR, new BeanPropertyRowMapper<T>(mappedClass));
	}

	public static Object[] obj(Object... o) {
		return o;
	}

	protected SqlArrayValue createArray(List data) {
		Assert.notNull(data, "List data must not be null");
		return new SqlArrayValue(data.toArray());
	}
}
