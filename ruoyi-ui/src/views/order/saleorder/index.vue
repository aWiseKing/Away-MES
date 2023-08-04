<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="下单日期" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择下单日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="要求交期" prop="requiredDeliveryTime">
        <el-date-picker clearable
          v-model="queryParams.requiredDeliveryTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择要求交期">
        </el-date-picker>
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
          v-hasPermi="['order:saleorder:add']"
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
          v-hasPermi="['order:saleorder:edit']"
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
          v-hasPermi="['order:saleorder:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['order:saleorder:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="saleorderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单id" align="center" prop="id" />
      <el-table-column label="下单日期" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" prop="createUserName" />
      <el-table-column label="需求数量" align="center" prop="number" />
      <el-table-column label="要求交期" align="center" prop="requiredDeliveryTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.requiredDeliveryTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="客户名称" align="center" prop="customerID" />
      <el-table-column label="当前订单状态" align="center" prop="state" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['order:saleorder:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['order:saleorder:remove']"
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

    <!-- 添加或修改订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="创建人" prop="createUserName">
          <div>{{ form.createUserName }}</div>
        </el-form-item>
        <el-form-item label="需求数量" prop="number">
          <el-input v-model="form.number" placeholder="请输入需求数量" />
        </el-form-item>
        <el-form-item label="要求交期" prop="requiredDeliveryTime">
          <el-date-picker clearable
            v-model="form.requiredDeliveryTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择要求交期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="客户信息" prop="customerID">
          <el-input v-model="form.customerID" placeholder="请输入客户信息" />
        </el-form-item>
        <el-form-item label="产品信息" prop="productID">
          <el-input v-model="form.productID" placeholder="请输入产品信息" />
        </el-form-item>
        <el-form-item label="合同信息" prop="contractID">
          <el-input v-model="form.contractID" placeholder="请输入合同信息" />
        </el-form-item>
        <el-form-item label="发票信息" prop="invoiceID">
          <el-input v-model="form.invoiceID" placeholder="请输入发票信息" />
        </el-form-item>
        <el-form-item label="订单状态" prop="state">
          <el-select v-model="form.state" placeholder="请选择">
            <el-option
              v-for="item in state_options"
              :key="item.key"
              :label="item.value"
              :value="item.key">
            </el-option>
          </el-select>
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
import { listSaleorder, getSaleorder, delSaleorder, addSaleorder, updateSaleorder } from "@/api/order/saleorder";

export default {
  name: "Saleorder",
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
      // 订单表格数据
      saleorderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否新建
      isadd: true,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        createTime: null,
        requiredDeliveryTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        createTime: [
          { required: true, message: "下单日期不能为空", trigger: "blur" }
        ],
        createUserName: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        number: [
          { required: true, message: "需求数量不能为空", trigger: "blur" }
        ],
        requiredDeliveryTime: [
          { required: true, message: "要求交期不能为空", trigger: "blur" }
        ],
        customerID: [
          { required: true, message: "客户信息不能为空", trigger: "blur" }
        ],
        productID: [
          { required: true, message: "产品信息不能为空", trigger: "blur" }
        ],
        state: [
          { required: true, message: "当前订单状态不能为空", trigger: "change" }
        ],
      },
      // 订单状态
      state_options:[
        {key:0,value:"未发布"},
        {key:1,value:"发布"},
        {key:2,value:"暂停"},
        {key:3,value:"完成"},
        {key:4,value:"取消"}
      ]
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询订单列表 */
    getList() {
      this.loading = true;
      listSaleorder(this.queryParams).then(response => {
        this.saleorderList = response.rows;
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
        createUserName: null,
        number: null,
        requiredDeliveryTime: null,
        customerID: null,
        productID: null,
        contractID: null,
        invoiceID: null,
        state: null
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.form.createUserName = document.cookie.split("username=")[1].split(";")[0]
      this.open = true;
      this.title = "添加订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSaleorder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSaleorder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSaleorder(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除订单编号为"' + ids + '"的数据项？').then(function() {
        return delSaleorder(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('order/saleorder/export', {
        ...this.queryParams
      }, `saleorder_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
