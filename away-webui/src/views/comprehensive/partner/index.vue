<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="实体姓名" prop="Name">
        <el-input
          v-model="queryParams.Name"
          placeholder="请输入实体姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="实体简称" prop="NameAbbrevation">
        <el-input
          v-model="queryParams.NameAbbrevation"
          placeholder="请输入实体简称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="社会统一信用代码" prop="UnifiedCreditCode">
        <el-input
          v-model="queryParams.UnifiedCreditCode"
          placeholder="请输入社会统一信用代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="证照附件url地址#多个附件地址用分号;分隔#" prop="certificateURL">
        <el-input
          v-model="queryParams.certificateURL"
          placeholder="请输入证照附件url地址#多个附件地址用分号;分隔#"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="城市地区id#省份、地市、区县#" prop="cityid">
        <el-input
          v-model="queryParams.cityid"
          placeholder="请输入城市地区id#省份、地市、区县#"
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
          v-hasPermi="['comprehensive:partner:add']"
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
          v-hasPermi="['comprehensive:partner:edit']"
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
          v-hasPermi="['comprehensive:partner:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['comprehensive:partner:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="partnerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="实体编号" align="center" prop="ID" />
      <el-table-column label="实体姓名" align="center" prop="Name" />
      <el-table-column label="实体简称" align="center" prop="NameAbbrevation" />
      <el-table-column label="社会统一信用代码" align="center" prop="UnifiedCreditCode" />
      <el-table-column label="证照附件url地址#多个附件地址用分号;分隔#" align="center" prop="certificateURL" />
      <el-table-column label="城市地区id#省份、地市、区县#" align="center" prop="cityid" />
      <el-table-column label="详细地址" align="center" prop="address" />
      <el-table-column label="备注信息" align="center" prop="notes" />
      <el-table-column label="实体类型" align="center" prop="type" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['comprehensive:partner:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['comprehensive:partner:remove']"
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

    <!-- 添加或修改合作方信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="实体姓名" prop="Name">
          <el-input v-model="form.Name" placeholder="请输入实体姓名" />
        </el-form-item>
        <el-form-item label="实体简称" prop="NameAbbrevation">
          <el-input v-model="form.NameAbbrevation" placeholder="请输入实体简称" />
        </el-form-item>
        <el-form-item label="社会统一信用代码" prop="UnifiedCreditCode">
          <el-input v-model="form.UnifiedCreditCode" placeholder="请输入社会统一信用代码" />
        </el-form-item>
        <el-form-item label="证照附件url地址#多个附件地址用分号;分隔#" prop="certificateURL">
          <el-input v-model="form.certificateURL" placeholder="请输入证照附件url地址#多个附件地址用分号;分隔#" />
        </el-form-item>
        <el-form-item label="城市地区id#省份、地市、区县#" prop="cityid">
          <el-input v-model="form.cityid" placeholder="请输入城市地区id#省份、地市、区县#" />
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
import { listPartner, getPartner, delPartner, addPartner, updatePartner } from "@/api/comprehensive/partner";

export default {
  name: "Partner",
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
      // 合作方信息表格数据
      partnerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        Name: null,
        NameAbbrevation: null,
        UnifiedCreditCode: null,
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
        Name: [
          { required: true, message: "实体姓名不能为空", trigger: "blur" }
        ],
        NameAbbrevation: [
          { required: true, message: "实体简称不能为空", trigger: "blur" }
        ],
        UnifiedCreditCode: [
          { required: true, message: "社会统一信用代码不能为空", trigger: "blur" }
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
    /** 查询合作方信息列表 */
    getList() {
      this.loading = true;
      listPartner(this.queryParams).then(response => {
        this.partnerList = response.rows;
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
        ID: null,
        Name: null,
        NameAbbrevation: null,
        UnifiedCreditCode: null,
        certificateURL: null,
        cityid: null,
        address: null,
        notes: null,
        type: null,
        isdel: null
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
      this.ids = selection.map(item => item.ID)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加合作方信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ID = row.ID || this.ids
      getPartner(ID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改合作方信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.ID != null) {
            updatePartner(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPartner(this.form).then(response => {
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
      const IDs = row.ID || this.ids;
      this.$modal.confirm('是否确认删除合作方信息编号为"' + IDs + '"的数据项？').then(function() {
        return delPartner(IDs);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('comprehensive/partner/export', {
        ...this.queryParams
      }, `partner_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
