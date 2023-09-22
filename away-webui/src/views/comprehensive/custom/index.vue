<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客户姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入客户姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户简称" prop="nameAbbrevation">
        <el-input
          v-model="queryParams.nameAbbrevation"
          placeholder="请输入客户简称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="社会统一信用代码" prop="unifiedCreditCode">
        <el-input
          v-model="queryParams.unifiedCreditCode"
          placeholder="请输入社会统一信用代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="证照附件" prop="certificateURL">
        <el-input
          v-model="queryParams.certificateURL"
          placeholder="请输入证照附件"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="城市" prop="cityid">
        <el-input
          v-model="queryParams.cityid"
          placeholder="请输入城市"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="详细地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入详细地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="备注信息" prop="notes">
        <el-input
          v-model="queryParams.notes"
          placeholder="请输入备注信息"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['comprehensive:custom:add']"
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
          v-hasPermi="['comprehensive:custom:edit']"
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
          v-hasPermi="['comprehensive:custom:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['comprehensive:custom:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="customList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="客户编号" align="center" prop="id" />
      <el-table-column label="客户姓名" align="center" prop="name" />
      <el-table-column label="客户简称" align="center" prop="nameAbbrevation" />
      <el-table-column label="社会统一信用代码" align="center" prop="unifiedCreditCode" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleShow(scope.row)"
            v-hasPermi="['comprehensive:custom:view']"
          >查看</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['comprehensive:custom:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['comprehensive:custom:remove']"
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

    <!-- 查看客户详细信息对话框 -->
    <el-dialog title="客户信息" :visible.sync="isshow" width="900px" append-to-body>
        <el-descriptions :title="form.name" border>
            <el-descriptions-item label="客户编号">{{ form.id }}</el-descriptions-item>
            <el-descriptions-item label="客户姓名">{{ form.name }}</el-descriptions-item>
            <el-descriptions-item label="客户简称">{{ form.nameAbbrevation }}</el-descriptions-item>
            <el-descriptions-item label="社会统一信用代码">{{ form.unifiedCreditCode }}</el-descriptions-item>
            <el-descriptions-item label="城市地区">{{ form.city }}</el-descriptions-item>
            <el-descriptions-item label="详细地址">{{ form.address }}</el-descriptions-item>
            <el-descriptions-item label="联系人信息">
              <el-table></el-table>
            </el-descriptions-item>
            <el-descriptions-item label="证照"></el-descriptions-item>
        </el-descriptions>
    </el-dialog>

    <!-- 添加或修改客户信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item v-if="isadd" label="客户编号" prop="id">
          <el-input v-model="form.id" placeholder="请输入客户编号" />
        </el-form-item>
        <el-form-item label="客户姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入客户姓名" />
        </el-form-item>
        <el-form-item label="客户简称" prop="nameAbbrevation">
          <el-input v-model="form.nameAbbrevation" placeholder="请输入客户简称" />
        </el-form-item>
        <el-form-item label="社会统一信用代码" prop="unifiedCreditCode">
          <el-input v-model="form.unifiedCreditCode" placeholder="请输入社会统一信用代码" minlength="18" maxlength="18"/>
        </el-form-item>
        <el-form-item label="城市地区" prop="cityid">
          <el-cascader
            :options="city_options"
            :props="{ expandTrigger: 'hover' }"
            :value="cityid_value"
            placeholder="请选择城市地区"
            @change="setCityID"
            clearable
            filterable
          >
          </el-cascader>
        </el-form-item>
        <el-form-item label="详细地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入详细地址" />
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
import { listCustom, getCustom, delCustom, addCustom, updateCustom } from "@/api/comprehensive/custom";
import { jsonCity } from "@/api/city/city";

export default {
  name: "Custom",
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
      // 客户信息表格数据
      customList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示表单细节
      isshow: false,
      // 是否新建
      isadd: true,
      // 城市地区选择器
      city_options:[],
      // 城市地区选择器值
      cityid_value:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        nameAbbrevation: null,
        unifiedCreditCode: null,
        certificateURL: null,
        cityid: null,
        address: null,
        notes: null,
        type: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "客户姓名不能为空", trigger: "blur" }
        ],
        nameAbbrevation: [
          { required: true, message: "客户简称不能为空", trigger: "blur" }
        ],
        unifiedCreditCode: [
          { required: true, message: "社会统一信用代码不能为空", trigger: "blur"},
          { min: 18, message: "社会统一信用代码不能少于18位", trigger: "blur"}
        ],
        type: [
          { required: true, message: "实体类型不能为空", trigger: "change" }
        ],
        isdel: [
          { required: true, message: "是否删除不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询客户信息列表 */
    getList() {
      this.loading = true;
      listCustom(this.queryParams).then(response => {
        this.customList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询城市地区json*/
    getJsonCity(){
      jsonCity({}).then(respones => {
        this.city_options = respones
      })
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
        nameAbbrevation: null,
        unifiedCreditCode: null,
        certificateURL: null,
        cityid: null,
        address: null,
        notes: null,
        type: 0,
        isdel: 0
      };
      this.resetForm("form");
    },
    setCityID(value){
      this.form.cityid = value[2]

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
    /** 查看详细按钮 */
    handleShow(row){
      this.isshow = true;
      this.form = row;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.getJsonCity();
      this.isadd = true;
      this.open = true;
      this.title = "添加客户信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getJsonCity();
      this.isadd = false;
      const id = row.id || this.ids
      getCustom(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客户信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (!this.isadd) {
            updateCustom(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCustom(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除客户信息编号为"' + ids + '"的数据项？').then(function() {
        return delCustom(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('comprehensive/custom/export', {
        ...this.queryParams
      }, `custom_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
