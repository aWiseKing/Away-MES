package com.awise.comprehensive.controller;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import  com.awise.comprehensive.domain.AwContacts;
import com.awise.comprehensive.service.IAwContactsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.away.common.annotation.Log;
import com.away.common.core.controller.BaseController;
import com.away.common.core.domain.AjaxResult;
import com.away.common.enums.BusinessType;
import com.away.common.utils.poi.ExcelUtil;
import com.away.common.core.page.TableDataInfo;

/**
 * 联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位Controller
 * 
 * @author ruoyi
 * @date 2023-09-22
 */
@RestController
@RequestMapping("/system/contacts")
public class AwContactsController extends BaseController
{
    @Autowired
    private IAwContactsService awContactsService;

    /**
     * 查询联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位列表
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:list')")
    @GetMapping("/list")
    public TableDataInfo list(AwContacts awContacts)
    {
        startPage();
        List<AwContacts> list = awContactsService.selectAwContactsList(awContacts);
        return getDataTable(list);
    }

    /**
     * 导出联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位列表
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:export')")
    @Log(title = "联系人信息 存储合作方的联系人的联系信息 存储  联系人姓名，电话，部门，职位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AwContacts awContacts)
    {
        List<AwContacts> list = awContactsService.selectAwContactsList(awContacts);
        ExcelUtil<AwContacts> util = new ExcelUtil<AwContacts>(AwContacts.class);
        util.exportExcel(response, list, "联系人信息 存储合作方的联系人的联系信息 存储  联系人姓名，电话，部门，职位数据");
    }

    /**
     * 获取联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(awContactsService.selectAwContactsById(id));
    }

    /**
     * 新增联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:add')")
    @Log(title = "联系人信息 存储合作方的联系人的联系信息 存储  联系人姓名，电话，部门，职位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AwContacts awContacts)
    {
        return toAjax(awContactsService.insertAwContacts(awContacts));
    }

    /**
     * 修改联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:edit')")
    @Log(title = "联系人信息 存储合作方的联系人的联系信息 存储  联系人姓名，电话，部门，职位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AwContacts awContacts)
    {
        return toAjax(awContactsService.updateAwContacts(awContacts));
    }

    /**
     * 删除联系人信息
存储合作方的联系人的联系信息
存储  联系人姓名，电话，部门，职位
     */
    @PreAuthorize("@ss.hasPermi('system:contacts:remove')")
    @Log(title = "联系人信息 存储合作方的联系人的联系信息 存储  联系人姓名，电话，部门，职位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(awContactsService.deleteAwContactsByIds(ids));
    }
}
