package com.paladin.qos.controller.school;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.paladin.common.core.container.ConstantsContainer;
import com.paladin.common.core.container.ConstantsContainer.KeyValue;
import com.paladin.framework.common.OffsetPage;
import com.paladin.framework.core.ControllerSupport;
import com.paladin.framework.core.query.QueryInputMethod;
import com.paladin.framework.web.response.CommonResponse;
import com.paladin.qos.service.epidemic.EpidemicSituationService;
import com.paladin.qos.service.school.OrgSchoolService;
import com.paladin.qos.service.school.dto.OrgSchoolCountsQuery;
import com.paladin.qos.service.school.dto.OrgSchoolQuery;
import com.paladin.qos.service.school.vo.OrgSchoolCountsGroupByNatureVO;
import com.paladin.qos.service.school.vo.OrgSchoolEpidemicRateVO;

/**
 * @author MyKite
 * @version 2019年7月23日 上午11:11:28
 */

@Controller
@RequestMapping("/qos/org/school/statistic")
public class OrgSchoolStatisticsController extends ControllerSupport {

	@Autowired
	OrgSchoolService orgSchoolService;
	@Autowired
	private EpidemicSituationService epidemicSituationService;

	@RequestMapping(value = "/index")
	@QueryInputMethod(queryClass = OrgSchoolQuery.class)
	public String index() {
		return "/qos/school/org_school_personnel_statistics";
	}

	@RequestMapping(value = "/doctor/index")
	@QueryInputMethod(queryClass = OrgSchoolQuery.class)
	public String doctorIndex() {
		return "/qos/school/org_school_doctor_statistics";
	}

	/**
	 * school_counts : 学校数展示
	 * student_counts : 学生数展示
	 * school_epidemic : 学校及疫情统计
	 * epidemic_situation : 全市疫情分析
	 * epidemic_rate : 罹患率统计
	 * epidemic_trace : 溯源指标
	 * @return
	 */
	@RequestMapping(value = "/view/{name}")
	@QueryInputMethod(queryClass = OrgSchoolCountsQuery.class)
	public String school_counts(@PathVariable("name") String name) {
		return "/qos/school/org_school_statistics_"+name;
	}
	
	/**
	 * 学校学额统计 统计每个学校学生总人数
	 * 
	 * @param query
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/personnel/count")
	@ResponseBody
	public Object SchoolPersonnelStatistics(OrgSchoolQuery query) {
		return CommonResponse.getSuccessResponse(orgSchoolService.schoolPersonnelCount(query));
	}

	/**
	 * 学校校医配备核查情况 统计每个学校专职校医,兼职校医,专职保健老师,兼职保健老师人数
	 * 
	 * @param query
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping("/doctor/count")
	@ResponseBody
	public Object SchoolDoctorStatistics(OrgSchoolQuery query) {
		return CommonResponse.getSuccessResponse(orgSchoolService.schoolDoctorCount(query));
	}
	
	/**
	 * 按隶属关系，统计学校数量
	 * @param query
	 * @return
	 */
	@RequestMapping("/school/countsbyAffiliation")
	@ResponseBody
	public Object schoolCountsbyAffiliation(OrgSchoolCountsQuery query) {
		List<KeyValue> type = ConstantsContainer.getType("subordination-type");
		Map<String,List<String>> affiliationGroup=new HashMap<>();
		for (KeyValue keyValue : type) {
			String affilication = keyValue.getValue();
			if(affilication.endsWith("镇公办")){
				affilication="区/镇公办";
			}
			List<String> list = affiliationGroup.get(affilication);
			if(CollectionUtils.isEmpty(list)){
				list=new ArrayList<String>();
			}
			list.add(keyValue.getKey());
			affiliationGroup.put(affilication, list);
		}
		Map<String,Object> result=new HashMap<String, Object>();
		List<String> affiGroup=new ArrayList<String>();
		List<List<OrgSchoolCountsGroupByNatureVO>>  data=new ArrayList<List<OrgSchoolCountsGroupByNatureVO>>();
		for (Entry<String,List<String>>  entry: affiliationGroup.entrySet()) {
			query.setAffiliations(entry.getValue());
			affiGroup.add(entry.getKey());
			data.add(orgSchoolService.schoolCountsGroupByNature(query));
		}
		List<String> natureGroup=new ArrayList<String>();
		List<KeyValue> type2 = ConstantsContainer.getType("nature-type");
		for (KeyValue keyValue : type2) {
			natureGroup.add(keyValue.getValue());
		}
		result.put("natureGroup", natureGroup);
		result.put("affiGroup", affiGroup);
		result.put("data", data);
		System.out.println(result);
		return CommonResponse.getSuccessResponse(result);
	}

	/**
	 * 按学校性质，统计学校数量
	 * @param query
	 * @return
	 */
	@RequestMapping("/school/countsbyNature")
	@ResponseBody
	public Object schoolCountsbyNature(OrgSchoolCountsQuery query) {
		List<KeyValue> type = ConstantsContainer.getType("nature-type");
		Map<String,Object> result=new HashMap<String, Object>();
		List<List<OrgSchoolCountsGroupByNatureVO>>  data=new ArrayList<List<OrgSchoolCountsGroupByNatureVO>>();
		List<String> group=new ArrayList<String>();
		for (KeyValue keyValue : type) {
			query.setNature(keyValue.getKey());
			group.add(keyValue.getValue());
			data.add(orgSchoolService.schoolCountsGroupByAffiliation(query));
		}
		List<String> affiGroup=new ArrayList<String>();
		List<KeyValue> type2 = ConstantsContainer.getType("subordination-type");
		for (KeyValue keyValue : type2) {
			String affilication = keyValue.getValue();
			if(affilication.endsWith("镇公办")){
				affilication="区/镇公办";
			}
			affiGroup.add(affilication);
		}
		result.put("affiGroup", affiGroup);
		result.put("natureGroup", group);
		result.put("data", data);
		System.out.println(result);
		return CommonResponse.getSuccessResponse(result);
	}
	
	/**
	 * 按隶属关系，统计学生人数
	 * @param query
	 * @return
	 */
	@RequestMapping("/people/countsbyAffiliation")
	@ResponseBody
	public Object peopleCountsbyAffiliation(OrgSchoolCountsQuery query) {
		List<KeyValue> type = ConstantsContainer.getType("subordination-type");
		Map<String,List<String>> affiliationGroup=new HashMap<>();
		for (KeyValue keyValue : type) {
			String affilication = keyValue.getValue();
			if(affilication.endsWith("镇公办")){
				affilication="区/镇公办";
			}
			List<String> list = affiliationGroup.get(affilication);
			if(CollectionUtils.isEmpty(list)){
				list=new ArrayList<String>();
			}
			list.add(keyValue.getKey());
			affiliationGroup.put(affilication, list);
		}
		Map<String,Object> result=new HashMap<String, Object>();
		List<String> affiGroup=new ArrayList<String>();
		List<List<OrgSchoolCountsGroupByNatureVO>>  data=new ArrayList<List<OrgSchoolCountsGroupByNatureVO>>();
		for (Entry<String,List<String>>  entry: affiliationGroup.entrySet()) {
			query.setAffiliations(entry.getValue());
			affiGroup.add(entry.getKey());
			data.add(orgSchoolService.schoolPeopleCountsGroupByNature(query));
		}
		List<String> natureGroup=new ArrayList<String>();
		List<KeyValue> type2 = ConstantsContainer.getType("nature-type");
		for (KeyValue keyValue : type2) {
			natureGroup.add(keyValue.getValue());
		}
		result.put("natureGroup", natureGroup);
		result.put("affiGroup", affiGroup);
		result.put("data", data);
		System.out.println(result);
		return CommonResponse.getSuccessResponse(result);
	}

	/**
	 * 按学校性质，统计学生人数
	 * @param query
	 * @return
	 */
	@RequestMapping("/people/countsbyNature")
	@ResponseBody
	public Object peopleCountsbyNature(OrgSchoolCountsQuery query) {
		List<KeyValue> type = ConstantsContainer.getType("nature-type");
		Map<String,Object> result=new HashMap<String, Object>();
		List<List<OrgSchoolCountsGroupByNatureVO>>  data=new ArrayList<List<OrgSchoolCountsGroupByNatureVO>>();
		List<String> group=new ArrayList<String>();
		for (KeyValue keyValue : type) {
			query.setNature(keyValue.getKey());
			group.add(keyValue.getValue());
			data.add(orgSchoolService.schoolPeopleCountsGroupByAffiliation(query));
		}
		List<String> affiGroup=new ArrayList<String>();
		List<KeyValue> type2 = ConstantsContainer.getType("subordination-type");
		for (KeyValue keyValue : type2) {
			String affilication = keyValue.getValue();
			if(affilication.endsWith("镇公办")){
				affilication="区/镇公办";
			}
			affiGroup.add(affilication);
		}
		result.put("affiGroup", affiGroup);
		result.put("natureGroup", group);
		result.put("data", data);
		System.out.println(result);
		return CommonResponse.getSuccessResponse(result);
	}
	/**
     * 按学校统计疫情次数
     * @param query
     * @return
     */
	@RequestMapping("/epidemic/counts")
	@ResponseBody
	public Object epidemicCounts(OrgSchoolCountsQuery query) {
		PageHelper.startPage(0, OffsetPage.DEFAULT_LIMIT);
		List<OrgSchoolCountsGroupByNatureVO> list=epidemicSituationService.epidemicCountsGroupByUnit(query);
		return CommonResponse.getSuccessResponse(list);
	}
	
	/**
     * 按学校统计疫情人数
     * @param query
     * @return
     */
	@RequestMapping("/epidemic/people/counts")
	@ResponseBody
	public Object epidemicPeopleCounts(OrgSchoolCountsQuery query) {
		PageHelper.startPage(0, OffsetPage.DEFAULT_LIMIT);
		List<OrgSchoolCountsGroupByNatureVO> list=epidemicSituationService.epidemicPeopleCountsGroupByUnit(query);
		return CommonResponse.getSuccessResponse(list);
	}
	
	/**
	 * 按隶属关系，统计学校数量
	 * @param query
	 * @return
	 */
	@RequestMapping("/epidemic/ratesbyAffiliation")
	@ResponseBody
	public Object epidemicRatesbyAffiliation(OrgSchoolCountsQuery query) {
		List<KeyValue> type = ConstantsContainer.getType("subordination-type");
		Map<String,List<String>> affiliationGroup=new HashMap<>();
		for (KeyValue keyValue : type) {
			String affilication = keyValue.getValue();
			if(affilication.endsWith("镇公办")){
				affilication="区/镇公办";
			}
			List<String> list = affiliationGroup.get(affilication);
			if(CollectionUtils.isEmpty(list)){
				list=new ArrayList<String>();
			}
			list.add(keyValue.getKey());
			affiliationGroup.put(affilication, list);
		}
		
		List<String> sickGroup=new ArrayList<String>();
		List<String> sickCodeGroup=new ArrayList<String>();
		List<KeyValue> type2 = ConstantsContainer.getType("sickness-type");
		for (KeyValue keyValue : type2) {
			sickCodeGroup.add(keyValue.getKey());
			sickGroup.add(keyValue.getValue());
		}
		Map<String,Object> result=new HashMap<String, Object>();
		List<String> affiGroup=new ArrayList<String>();
		List<List<Object>> serialData1=new ArrayList<List<Object>>();
		List<List<Object>> serialData2=new ArrayList<List<Object>>();
		
		for (String sickCode : sickCodeGroup) {
			query.setSicknessClassify(sickCode);
			//System.out.println("sickCode--->"+sickCode+"---"+ConstantsContainer.getTypeValue("sickness-type",sickCode));
			List<Object> data1=new ArrayList<Object>();
			List<Object> data2=new ArrayList<Object>();
			for (Entry<String,List<String>>  entry: affiliationGroup.entrySet()) {
				query.setAffiliations(entry.getValue());
				if(!affiGroup.contains(entry.getKey())){
					affiGroup.add(entry.getKey());
				}
				List<OrgSchoolEpidemicRateVO> list = epidemicSituationService.queryEpidemicRatesByAffiliation(query);
				Set<String> schoolIdSet=new HashSet<String>(); 
				Set<String> orgSchoolPeopleIdSet=new HashSet<String>(); 
				Set<String> incidentOrgSchoolPeopleSet=new HashSet<String>(); 
				List<Integer> list2=new ArrayList<Integer>();
				list2.add(0);//schoolSum
				list2.add(0);//orgSchoolPeopleSum
				list2.add(0);//incidentSum
				for (OrgSchoolEpidemicRateVO rateVO : list) {
					if(!schoolIdSet.contains(rateVO.getSchoolId())){
						schoolIdSet.add(rateVO.getSchoolId());
						list2.set(0,list2.get(0)+rateVO.getShcoolTotal());//全校总人数
					}
					if(!incidentOrgSchoolPeopleSet.contains(rateVO.getIncidentOrgSchoolPeopleId())){
						if(!orgSchoolPeopleIdSet.contains(rateVO.getOrgSchoolPeopleId())){
							orgSchoolPeopleIdSet.add(rateVO.getOrgSchoolPeopleId());
							list2.set(1,list2.get(1)+rateVO.getOrgSchoolPeopleTotal());//班级人数
						}
						incidentOrgSchoolPeopleSet.add(rateVO.getIncidentOrgSchoolPeopleId());
						list2.set(2,list2.get(2)+rateVO.getIncidentTotol());//患病人数
					}
				}
				
				if(list2.get(2)>0){
					data1.add(round(list2.get(2).toString(),list2.get(1).toString(),2));
					data2.add(round(list2.get(2).toString(),list2.get(0).toString(),2));
				}else{
					data1.add(0);
					data2.add(0);
				}
				//System.out.println(entry.getKey()+list2);
			}
			//System.out.println("-------班级罹患率------->"+data1);
			//System.out.println("-------全校罹患率-------->"+data2);
			serialData1.add(data1);
			serialData2.add(data2);
		}
		result.put("affiGroup", affiGroup);
		result.put("sickGroup", sickGroup);
		result.put("serialData1", serialData1);
		result.put("serialData2", serialData2);
		//System.out.println(result);
		return CommonResponse.getSuccessResponse(result);
	}
	
	/**
	 * 学校性质，统计学校数量
	 * @param query
	 * @return
	 */
	@RequestMapping("/epidemic/ratesbyNature")
	@ResponseBody
	public Object epidemicRatesbyNature(OrgSchoolCountsQuery query) {
		List<KeyValue> type = ConstantsContainer.getType("nature-type");
		Map<String,String> affiliationMap=new HashMap<>();
		for (KeyValue keyValue : type) {
			affiliationMap.put( keyValue.getValue(),keyValue.getKey());
		}
		
		List<String> sickGroup=new ArrayList<String>();
		List<String> sickCodeGroup=new ArrayList<String>();
		List<KeyValue> type2 = ConstantsContainer.getType("sickness-type");
		for (KeyValue keyValue : type2) {
			sickCodeGroup.add(keyValue.getKey());
			sickGroup.add(keyValue.getValue());
		}
		Map<String,Object> result=new HashMap<String, Object>();
		List<String> natureGroup=new ArrayList<String>();
		List<List<Object>> serialData1=new ArrayList<List<Object>>();
		List<List<Object>> serialData2=new ArrayList<List<Object>>();
		
		for (String sickCode : sickCodeGroup) {
			query.setSicknessClassify(sickCode);
			//System.out.println("sickCode--->"+sickCode+"---"+ConstantsContainer.getTypeValue("sickness-type",sickCode));
			List<Object> data1=new ArrayList<Object>();
			List<Object> data2=new ArrayList<Object>();
			for (Entry<String, String>  entry: affiliationMap.entrySet()) {
				query.setNature(entry.getValue());
				if(!natureGroup.contains(entry.getKey())){
					natureGroup.add(entry.getKey());
				}
				List<OrgSchoolEpidemicRateVO> list = epidemicSituationService.queryEpidemicRatesByNature(query);
				Set<String> schoolIdSet=new HashSet<String>(); 
				Set<String> orgSchoolPeopleIdSet=new HashSet<String>(); 
				Set<String> incidentOrgSchoolPeopleSet=new HashSet<String>(); 
				List<Integer> list2=new ArrayList<Integer>();
				list2.add(0);//schoolSum
				list2.add(0);//orgSchoolPeopleSum
				list2.add(0);//incidentSum
				for (OrgSchoolEpidemicRateVO rateVO : list) {
					if(!schoolIdSet.contains(rateVO.getSchoolId())){
						schoolIdSet.add(rateVO.getSchoolId());
						list2.set(0,list2.get(0)+rateVO.getShcoolTotal());//全校总人数
					}
					if(!incidentOrgSchoolPeopleSet.contains(rateVO.getIncidentOrgSchoolPeopleId())){
						if(!orgSchoolPeopleIdSet.contains(rateVO.getOrgSchoolPeopleId())){
							orgSchoolPeopleIdSet.add(rateVO.getOrgSchoolPeopleId());
							list2.set(1,list2.get(1)+rateVO.getOrgSchoolPeopleTotal());//班级人数
						}
						incidentOrgSchoolPeopleSet.add(rateVO.getIncidentOrgSchoolPeopleId());
						list2.set(2,list2.get(2)+rateVO.getIncidentTotol());//患病人数
					}
				}
				
				if(list2.get(2)>0){
					data1.add(round(list2.get(2).toString(),list2.get(1).toString(),2));
					data2.add(round(list2.get(2).toString(),list2.get(0).toString(),2));
				}else{
					data1.add(0);
					data2.add(0);
				}
				//System.out.println(entry.getKey()+list2);
			}
			//System.out.println("-------班级罹患率------->"+data1);
			//System.out.println("-------全校罹患率-------->"+data2);
			serialData1.add(data1);
			serialData2.add(data2);
		}
		result.put("natureGroup", natureGroup);
		result.put("sickGroup", sickGroup);
		result.put("serialData1", serialData1);
		result.put("serialData2", serialData2);
		//System.out.println(result);
		return CommonResponse.getSuccessResponse(result);
	}
	
	

	/**
     * 提供精确的小数位四舍五入处理。
     * @param divisor 除数
     * @param dividend 被除数
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
	public Double round(String divisor, String dividend, int scale) {
		if (scale < 0) {
		    throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal divisorBig = new BigDecimal(divisor);
		BigDecimal dividendBig = new BigDecimal(dividend);
		return divisorBig.divide(dividendBig, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
