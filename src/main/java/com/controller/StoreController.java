package com.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bean.Drug;
import com.bean.Manager;
import com.bean.Store;
import com.service.DruginfoService;
import com.service.StoreService;


@Controller
public class StoreController {
	@Autowired
	StoreService SS;
	
	@Autowired
	DruginfoService DS2;
	
	@RequestMapping("/store")  //store 
	public String to_store(HttpSession hs){
		Manager fl = (Manager) hs.getAttribute("login");
		if(fl!=null) { 
			return "store/store";
		}else{
			return "../../index";
		}
	}
	@RequestMapping("/storeinfo")
	public String to_storeinfo(HttpSession hs,Map<String,Object> map){
			Manager fl = (Manager) hs.getAttribute("login");
			if(fl!=null) { 
				map.put("queryresult",SS.QueryStoreService(1,""));
				map.put("querystring", "");
				return "store/storeinfo";
			}else{
				return "../../index";
			}
		}
	@RequestMapping("/addstore")  //storeinfo 
	public String to_addstore(HttpSession hs){
		Manager fl = (Manager) hs.getAttribute("login");
		if(fl!=null) { 
			return "store/addstore";
		}else{
			return "../../index";
		}
	}
	@RequestMapping("/querystore")  //storeinfo 
	public String to_querystore(@RequestParam(value="pn",defaultValue="1")Integer pn,
			@RequestParam(value="querystore")String qd,HttpSession hs,
			Map<String,Object> map){
		Manager fl = (Manager) hs.getAttribute("login");
		if(fl!=null) { 
			map.put("queryresult",SS.QueryStoreService(pn,qd));
			map.put("querystring", qd);
			return "store/querystore";
		}else{
			return "../../index";
		}
	}
	@RequestMapping("/querystore2")   //�����������Ҫ�Ż��������ܣ�ǰ�˿���ʹ��JSON��ƽ������
	public String to_querystore2(@RequestParam(value="pn",defaultValue="1")Integer pn,
			@RequestParam(value="querystore")String qd,HttpSession hs,
			Map<String,Object> map){
		Manager fl = (Manager) hs.getAttribute("login");
		if(fl!=null) { 			
			map.put("queryresult",SS.QueryStoreService(pn,qd));
			map.put("querystring", qd);
			return "store/storeinfo";
		}else{
			return "../../index";
		}
	}
   @RequestMapping(value="/deletestore",method=RequestMethod.POST)
	public String to_deletestore(HttpSession hs,String pn,String qd,String drugname,String changshang,
			String pihao,RedirectAttributes attr){
		
		Manager fl = (Manager) hs.getAttribute("login");
		if(fl!=null) { 
			SS.DeleteService(drugname,changshang,pihao);
			attr.addAttribute("pn", pn);
			attr.addAttribute("querystore", qd);
			return "redirect:/querystore";
		}else{
			return "../../index";
		}
	}
   @RequestMapping(value="/deletestore2",method=RequestMethod.POST)
  	public String to_deletestore2(HttpSession hs,String pn,String qd,String drugname,String changshang,
  			String pihao,RedirectAttributes attr){
  		
  		Manager fl = (Manager) hs.getAttribute("login");
  		if(fl!=null) { 
  			SS.DeleteService(drugname,changshang,pihao);
  			attr.addAttribute("pn", pn);
  			attr.addAttribute("querystore", qd);
  			return "redirect:/querystore2";
  		}else{
  			return "../../index";
  		}
  	}
   
   
    
    @RequestMapping(value="/updatestore",method=RequestMethod.POST)
	public String to_updatestore(HttpSession hs,@RequestParam(value="nowpage",defaultValue="1")Integer pn,
			String nowqd,Map<String,Object> map,@Valid Store reqstore,BindingResult result){
		Manager fl = (Manager) hs.getAttribute("login");
		if(fl!=null) { 
			if(result.hasErrors()){
				map.put("queryresult",SS.QueryStoreService(pn,nowqd));
				map.put("querystring", nowqd);
				map.put("msg", "�޸�ʧ��:ʱ���ʽ����,����������Ҫ�ǹ�ȥ��ʱ��,��Ч����Ҫ�ǽ�����ʱ��");	
				return "store/querystore";
			/*	List<FieldError> err = result.getFieldErrors();
				if(err.size()==1){
					map.put("msg", "�޸�ʧ��:"+err.get(0).getField()+err.get(0).getDefaultMessage());
					return "store/querystore";
				}else{
					map.put("msg", "�޸�ʧ��:"+err.get(0).getField()+err.get(0).getDefaultMessage()+
							","+err.get(1).getField()+err.get(1).getDefaultMessage());   
					return "store/querystore";
				}    */
			}else{
				SS.UpdateStoreService(reqstore);
				map.put("queryresult",SS.QueryStoreService(pn,nowqd));
				map.put("querystring", nowqd);
				map.put("msg", "�޸ĳɹ���");
				return "store/querystore";
			}
		}else{
			return "../../index";
		}
	}
    
    @RequestMapping(value="/updatestore2",method=RequestMethod.POST)
	public String to_updatestore2(HttpSession hs,@RequestParam(value="nowpage",defaultValue="1")Integer pn,
			String nowqd,Map<String,Object> map,@Valid Store reqstore,BindingResult result){
		Manager fl = (Manager) hs.getAttribute("login");
		if(fl!=null) { 
			if(result.hasErrors()){
				map.put("queryresult",SS.QueryStoreService(pn,nowqd));
				map.put("querystring", nowqd);
				map.put("msg", "�޸�ʧ��:ʱ���ʽ����,����������Ҫ�ǹ�ȥ��ʱ��,��Ч����Ҫ�ǽ�����ʱ��");	
				return "store/storeinfo";
			}else{
				SS.UpdateStoreService(reqstore);
				map.put("queryresult",SS.QueryStoreService(pn,nowqd));
				map.put("querystring", nowqd);
				map.put("msg", "�޸ĳɹ���");
				return "store/storeinfo";
			}
		}else{
			return "../../index";
		}
	}
   /* @ResponseBody
    @RequestMapping(value="/updatestore2",method=RequestMethod.POST)
	public List<FieldError> to_updatestore2(@RequestParam(value="nowpage",defaultValue="1")Integer pn,
			String nowqd,@Valid Store reqstore,BindingResult result){
		 
			if(result.hasErrors()){
				
				List<FieldError> err = result.getFieldErrors();
				
				return err;
			}return null;
	}*/
    @RequestMapping("/selectdrug")  //store 
	public String to_selectdrug(HttpSession hs,String queryselectdrug,Map<String,Object> map){
		Manager fl = (Manager) hs.getAttribute("login");
		if(fl!=null) { 
			map.put("selectinfo", DS2.QueryDrugService(queryselectdrug));
			return "store/selectpage";
		}else{
			return "../../index";
		}
	}
    /*
    <td>
	<a class="btn btn-info btn-sm" href="<%=basePath%>toaddstore?drugname=${ds.drugname }&changshang=${ds.changshang }&beizhu=${ds.beizhu }&location=${ds.location }&unit=${ds.unit }&guige=${ds.guige }&tiaoxingma=${ds.tiaoxingma }" role="button">
		<span class="glyphicon glyphicon-hand-up" aria-hidden="true"></span>ѡ����Ӵ�ҩƷ
	</a>
</td>*/
    @RequestMapping(value="/toaddstore",method=RequestMethod.POST)  
	public String toaddstore(HttpSession hs,@Valid Drug drug,Map<String,Object> map){
		Manager fl = (Manager) hs.getAttribute("login");
		if(fl!=null) { 
			//System.out.println(drug);
			map.put("drugstoreinfo", drug);
			return "store/addstoreinfo";
		}else{
			return "../../index";
		}
	}
    //addtostore
    @RequestMapping(value="/addtostore",method=RequestMethod.POST)
   	public String to_addtostore(HttpSession hs,Map<String,Object> map,@Valid Store store,BindingResult result){
   		Manager fl = (Manager) hs.getAttribute("login");
   		if(fl!=null) { 
   			if(result.hasErrors()){
   				Drug dd = new Drug(store.getDrugname(), store.getChangshang(), store.getBeizhu(), store.getLocation(), store.getUnit(), store.getGuige(), store.getTiaoxingma());
   				map.put("msg", "�޸�ʧ��:ʱ���ʽ����,����������Ҫ�ǹ�ȥ��ʱ��,��Ч����Ҫ�ǽ�����ʱ��");	
   				map.put("drugstoreinfo", dd);
   				return "store/addstoreinfo";
   			}else{
   				if(SS.AddStoreService(store)){
   					map.put("msg","����ͬ����ҩƷ����ӳɹ�");
   					return "store/addstore";
   				}else{
   					map.put("msg","��ӳɹ�");
   					return "store/addstore";
   				}
   			}
   		}else{
   			return "../../index";
   		}
   	}
    //deletetip
    @RequestMapping(value="/deletetip",method=RequestMethod.POST)
	public String to_deletetip(HttpSession hs,String drugname,String changshang,
			String pihao){
		
		Manager fl = (Manager) hs.getAttribute("login");
		if(fl!=null) { 
			SS.DeleteService(drugname,changshang,pihao);			
			return "redirect:/date";
		}else{
			return "../../index";
		}
	}
}
