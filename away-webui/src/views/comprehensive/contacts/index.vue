<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
    <el-row :gutter="1">
            <el-col :span="21">
              <div style="overflow-x: auto;scrollbar-width: none; white-space: nowrap;">
      <el-form-item label="联系人姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入联系人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人电话" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入联系人电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人部门" prop="department">
        <el-input
          v-model="queryParams.department"
          placeholder="请输入联系人部门"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人职位" prop="position">
        <el-input
          v-model="queryParams.position"
          placeholder="请输入联系人职位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人所属的定位" prop="location">
        <el-select v-model="queryParams.location" placeholder="请选择联系人所属的定位" clearable>
          <el-option
            v-for="dict in dict.type.aw_contract_location"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="联系人所属公司id" prop="companyID">
        <el-input
          v-model="queryParams.companyID"
          placeholder="请输入联系人所属公司id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
 </div>
        </el-col>
        <el-col :span="3">
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
      </el-col>
    </el-row>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['comprehensive:contacts:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['comprehensive:contacts:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['comprehensive:contacts:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['comprehensive:contacts:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="contactsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="联系人信息id" align="center" prop="id" />
      <el-table-column label="联系人姓名" align="center" prop="name" />
      <el-table-column label="联系人电话" align="center" prop="phone" />
      <el-table-column label="联系人部门" align="center" prop="department" />
      <el-table-column label="联系人职位" align="center" prop="position" />
      <el-table-column label="联系人所属的定位" align="center" prop="location">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.aw_contract_location" :value="scope.row.location"/>
        </template>
      </el-table-column>
      <el-table-column label="联系人所属公司id" align="center" prop="companyID" />
      <el-table-column label="备注信息" align="center" prop="notes" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
        <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row)"
            v-hasPermi="['comprehensive:contacts:edit']"
          >查看</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['comprehensive:contacts:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['comprehensive:contacts:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改联系人信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="联系人姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入联系人姓名" />
        </el-form-item>
        <el-form-item label="联系人电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入联系人电话" />
        </el-form-item>
        <el-form-item label="联系人部门" prop="department">
          <el-input v-model="form.department" placeholder="请输入联系人部门" />
        </el-form-item>
        <el-form-item label="联系人职位" prop="position">
          <el-input v-model="form.position" placeholder="请输入联系人职位" />
        </el-form-item>
        <el-form-item label="联系人所属的定位" prop="location">
          <el-select v-model="form.location" placeholder="请选择联系人所属的定位">
            <el-option
              v-for="dict in dict.type.aw_contract_location"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="联系人所属公司id" prop="companyID">
          <el-input v-model="form.companyID" placeholder="请输入联系人所属公司id" />
        </el-form-item>
        <el-form-item label="备注信息" prop="notes">
          <el-input v-model="form.notes" placeholder="请输入备注信息" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listContacts, getContacts, delContacts, addContacts, updateContacts } from "@/api/comprehensive/contacts";

export default {
  name: "Contacts",
  dicts: ['aw_contract_location'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 联系人信息表格数据
      contactsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示查看窗口
      view_open: false,
      // 是否新增
      isadd: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        phone: null,
        department: null,
        position: null,
        location: null,
        companyID: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询联系人信息列表 */
    getList() {
      this.loading = true;
      listContacts(this.queryParams).then(response => {
        this.contactsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        phone: null,
        department: null,
        position: null,
        location: null,
        companyID: null,
        notes: null,
        isDel: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    handleView(row) {
      this.view_open = true;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.isadd = true;
      this.open = true;
      this.title = "添加联系人信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.isadd = false;
      const id = row.id || this.ids
      getContacts(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改联系人信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (!this.isadd) {
            updateContacts(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addContacts(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除联系人信息编号为"' + ids + '"的数据项？').then(function() {
        return delContacts(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('comprehensive/contacts/export', {
        ...this.queryParams
      }, `contacts_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
