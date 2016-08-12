package ua.com.csltd.dao.impl;

/**
 * Created by valeriy_solyanik
 * on 09.08.2016.
 */

import oracle.jdbc.OracleTypes;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.support.oracle.SqlArrayValue;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import ua.com.csltd.beans.entity.PdfFileEntity;
import ua.com.csltd.beans.entity.PdfGroupEntity;
import ua.com.csltd.beans.entity.PdfOrderEntityBean;
import ua.com.csltd.beans.entity.PdfOrderFieldEntityBean;
import ua.com.csltd.util.ArrayTypeEditor;
import ua.com.csltd.util.BeanPropertyArrayStructMapper;
import ua.com.csltd.util.SqlReturnCollection;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: makiyov
 * Date: 29.03.13
 * Time: 13:46
 */
@Repository
public class PdfOrderCBDAOImpl {

	@Autowired
	private SimpleJdbcCall simpleJdbcCallIfobs;

	public List<PdfOrderEntityBean> getDataInfo(List<BigDecimal> ids) {
		return simpleJdbcCallIfobs.withCatalogName("PKG_IFOBSGATE")
				.withFunctionName("GetPdfOrderInfo")
				.declareParameters(getPdfOrderTab(),
						arrayParamDataInfo("in_id_tab"))
				.executeFunction(List.class, new SqlArrayValue(getEntityIds(ids)));
	}

	public Object[] getEntityIds(List<BigDecimal> ids) {
		Object[] data = new Object[ids.size()];
		for (int i = 0; i < ids.size(); i++) {
			data[i] = ids.get(i);
		}

		return data;
	}

	private SqlOutParameter getPdfOrderTab() {
		return new SqlOutParameter("return",
				OracleTypes.ARRAY,
				"PDFORDER_TAB",
				new SqlReturnCollection<PdfOrderEntityBean>(PdfOrderEntityBean.class) {
					public void registryEditor(BeanWrapper bw) {
						bw.registerCustomEditor(List.class, "pdfField_tab", new ArrayTypeEditor(new BeanPropertyArrayStructMapper(PdfOrderFieldEntityBean.class)));
						bw.registerCustomEditor(List.class, "pdffiles", new ArrayTypeEditor(new BeanPropertyArrayStructMapper(PdfFileEntity.class) {
							@Override
							public void registerCustomEditor(BeanWrapper bw) {
								bw.registerCustomEditor(List.class, "pdfgroups", new ArrayTypeEditor(new BeanPropertyArrayStructMapper(PdfGroupEntity.class)));
							}
						}));
					}
				});
	}

	protected static SqlParameter arrayParamDataInfo(String name) {
		return new SqlParameter(name, OracleTypes.ARRAY, "NUMBER_ARRAY");
	}

  /*  public static SqlOutParameter createBeanOutParameter() {
        return new SqlOutParameter("return",
                OracleTypes.ARRAY,
                SystemConst.IFOBS_SCHEMA_NAME + ".PDFORDER_TAB",
                new SqlReturnType() {
                    @Override
                    public Object getTypeValue(CallableStatement cs, int paramIndex, int sqlType, String typeName) throws SQLException {
                        List<PdfOrderEntityBean> beans = new ArrayList<PdfOrderEntityBean>();
                        ARRAY array = (ARRAY) cs.getObject(paramIndex);
                        if (array != null) {
                            Object[] dataArray = (Object[]) array.getArray();
                            if (dataArray != null) {
                                for (Object o : dataArray) {
                                    Object[] attributes = ((STRUCT) o).getAttributes();
                                    if (attributes != null) {
                                        beans.add(new PdfOrderEntityBean(attributes));
                                    } else {
                                        throw new EmptyDataException("Attributes is null");
                                    }
                                }
                            } else {
                                throw new EmptyDataException("Array data is null");
                            }
                        } else {
                            throw new EmptyDataException("Array is null");
                        }

                        return beans;
                    }
                });
    }*/

}
