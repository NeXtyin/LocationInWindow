package cn.jianke.getlocationinwindowdemo.httprequest.utils;

import org.json.JSONObject;
import java.util.Map;
import cn.jianke.getlocationinwindowdemo.httprequest.InterfaceParameters;
import cn.jianke.getlocationinwindowdemo.module.util.StringUtil;

/**
 * @className: TransUtils
 * @classDescription: 接口请求工具类
 * @author: leibing
 * @createTime: 2016/08/30
 */
public class TransUtil {

	/**
	 * 将TransData转出为JSON字符串
	 * @author leibing
	 * @createTime 2016/08/30
	 * @lastModify 2016/08/30
	 * @param map 参数名-值
	 * @return JSON数据
	 */
    public static String listToJson(Map<String,String> map ){
		JSONObject json = new JSONObject();
		try{
			for(Map.Entry<String,String> entry : map.entrySet()){
				 json.put(entry.getKey(), entry.getValue());
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return json.toString();
	}

	/**
	 * 将JSON字符串转成TransData
	 * @author leibing
	 * @createTime 2016/08/30
	 * @lastModify 2016/08/30
	 * @param result json字符
	 * @return TransData
	 */
    public static TransData getResponse(String result){
		TransData response = null;
		if( StringUtil.isNotEmpty(result) ){
			try{			
				response = new TransData();
				JSONObject jsonObject = new JSONObject(result);
				if (StringUtil.isNotEmpty(jsonObject.optString(InterfaceParameters.ERROR_CODE))) {
					response.setErrorcode(jsonObject.optString(InterfaceParameters.ERROR_CODE));
					response.setInfo(jsonObject.optString(InterfaceParameters.INFO));
					response.setErrormsg(jsonObject.optString(InterfaceParameters.ERROR_MSG));
				}else if (StringUtil.isNotEmpty(jsonObject.optString(InterfaceParameters.CODE))){
					response.setStatus(jsonObject.optString(InterfaceParameters.STATUS));
					response.setData(jsonObject.optString(InterfaceParameters.DATA));
					response.setCode(jsonObject.optString(InterfaceParameters.CODE));
					response.setMsg(jsonObject.optString(InterfaceParameters.MSG));
				}
            }catch(Exception e){
				e.printStackTrace();
			}
		}		
		return response;
	}
}
