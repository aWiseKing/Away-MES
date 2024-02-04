<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-row :gutter="1">
        <el-col :span="21">
          <div
            style="overflow-x: auto; scrollbar-width: none; white-space: nowrap"
          >
    
            <el-form-item label="产品图号" prop="productID">
              <el-input
                v-model="queryParams.productID"
                placeholder="请输入产品图号"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="产品名称" prop="productname">
              <el-input
                v-model="queryParams.productname"
                placeholder="请输入产品名称"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="出货检验编号" prop="shippingInspectionID">
              <el-input
                v-model="queryParams.shippingInspectionID"
                placeholder="请输入出货检验编号"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="客户姓名" prop="customname">
              <el-input
                v-model="queryParams.customname"
                placeholder="请输入客户姓名"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="3">
          <el-form-item>
            <el-button
              type="primary"
              icon="el-icon-search"
              size="mini"
              @click="handleQuery"
              >搜索</el-button
            >
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
              >重置</el-button
            >
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
          v-hasPermi="['produce:detailproductoutbound:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['produce:detailproductoutbound:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['produce:detailproductoutbound:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['produce:detailproductoutbound:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="detailproductoutboundList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="出库单编号"
        align="center"
        prop="deliveryNoteID"
      />
      <el-table-column label="产品图号" align="center" prop="productID" />
      <el-table-column label="产品名称" align="center" prop="productname" />
      <el-table-column
        label="出货检验编号"
        align="center"
        prop="shippingInspectionID"
      />
      <el-table-column label="出库数量" align="center" prop="receiptQuantity" />
      <el-table-column label="客户姓名" align="center" prop="customname" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row)"
            v-hasPermi="['produce:detailproductoutbound:edit']"
            >查看</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['produce:detailproductoutbound:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['produce:detailproductoutbound:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改产品出库详单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" :disabled="view_open" label-width="80px">
        <el-row :gutter="12"
          ><el-col :span="12">
            <el-form-item label="出库单编号" prop="deliveryNoteID">
              <el-input
                v-model="form.deliveryNoteID"
                placeholder="请输入出库单编号"
              /> </el-form-item></el-col
        ></el-row>
        <el-row :gutter="12"
          ><el-col :span="12">
            <el-form-item label="出库数量" prop="receiptQuantity">
              <el-input
                v-model="form.receiptQuantity"
                placeholder="请输入出库数量"
              /> </el-form-item></el-col
        ></el-row>

        <el-row :gutter="12"
          ><el-col :span="12">
            <el-form-item label="产品图号" prop="productID">
              <el-select
                v-model="form.productID"
                placeholder="请选择产品图号"
                @focus="getListproduct()"
              >
                <el-option
                  v-for="(item, index) in productlist"
                  :key="index"
                  :label="item.id"
                  :value="item.id"
                  @click.native="setDeliveryNoteOfProduct(item.id)"
                >
                </el-option>
              </el-select> </el-form-item></el-col
          ><el-col :span="12">
            <el-form-item label="产品名称" prop="productname">
              <el-input
                v-model="deliveryNote.productname"
                placeholder="请输入产品名称"
                disabled
              /> </el-form-item></el-col
        ></el-row>
        <el-row :gutter="12">
          <el-col :span="12">
            <el-form-item label="客户编号" prop="contractID">
              <el-select
                v-model="form.contractID"
                placeholder="请选择客户编号"
                @focus="getListcustom()"
              >
                <el-option
                  v-for="(item, index) in customlist"
                  :key="index"
                  :label="item.name"
                  :value="item.id"
                  @click.native="setDeliveryNoteOfCustom(item.id)"
                >
                </el-option>
              </el-select> </el-form-item
          ></el-col>
          <el-col :span="12">
            <el-form-item label="客户姓名" prop="customname">
              <el-input
                v-model="deliveryNote.customname"
                placeholder="请输入客户姓名"
                disabled
              /> </el-form-item></el-col
        ></el-row>
        <el-row :gutter="12"
          ><el-col :span="12">
            <el-form-item label="出货检验编号" prop="shippingInspectionID">
              <el-select
                v-model="form.shippingInspectionID"
                placeholder="请选择出货检验编号"
                @focus="getListshippinginspection()"
              >
                <el-option
                  v-for="(item, index) in shippinginspectionlist"
                  :key="index"
                  :label="item.id"
                  :value="item.id"
                  @click.native="setDeliveryNoteOfShippinginspection(item.id)"
                >
                </el-option>
              </el-select> </el-form-item
          ></el-col>
          <el-col :span="12">
            <el-form-item label="出货数量" prop="shipmentQuantity">
              <el-input
                v-model="deliveryNote.shipmentQuantity"
                placeholder="请输入出货数量"
                disabled
              /> </el-form-item></el-col></el-row
        ><el-row :gutter="12"
          ><el-col :span="12">
            <el-form-item label="检测数量" prop="detectionQuantity">
              <el-input
                v-model="deliveryNote.detectionQuantity"
                placeholder="请输入检测数量"
                disabled
              /> </el-form-item></el-col
          ><el-col :span="12">
            <el-form-item label="合格数量" prop="qualifiedQuantity">
              <el-input
                v-model="deliveryNote.qualifiedQuantity"
                placeholder="请输入合格数量"
                disabled
              /> </el-form-item></el-col></el-row
        ><el-row :gutter="12"
          ><el-col :span="12">
            <el-form-item label="不合格数量" prop="unqualifiedQuantity">
              <el-input
                v-model="deliveryNote.unqualifiedQuantity"
                placeholder="请输入不合格数量"
                disabled
              /> </el-form-item></el-col
          ><el-col :span="12">
            <el-form-item label="检测结果" prop="testResult">
              <el-select
                v-model="deliveryNote.testResult"
                placeholder="请选择检测结果"
                disabled
              >
                <el-option
                  v-for="dict in dict.type.aw_quality_shippinginspection_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select> </el-form-item></el-col></el-row
        ><el-row :gutter="12"
          ><el-col :span="12">
            <el-form-item label="检测日期" prop="testDate">
              <el-date-picker
                clearable
                v-model="deliveryNote.testDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择检测日期"
                disabled
              >
              </el-date-picker> </el-form-item></el-col
          ><el-col :span="12">
            <el-form-item label="检测人员" prop="testingPersonnel">
              <el-input
                v-model="deliveryNote.testingPersonnel"
                placeholder="请输入检测人员"
                disabled
              /> </el-form-item></el-col
        ></el-row>
      </el-form>
      <div slot="footer" v-if="!view_open" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listDetailproductoutbound,
  getDetailproductoutbound,
} from "@/api/produce/detailproductoutbound";
import { listProduct, getProduct } from "@/api/order/product";
import {
  listShippinginspection,
  getShippinginspection,
} from "@/api/quality/shippinginspection.js";
import { listCustom, getCustom } from "@/api/comprehensive/custom.js";
import {
  getProductoutbound,
  addProductoutbound,
  updateProductoutbound,
  delProductoutbound,
} from "@/api/produce/productoutbound.js";
import '@/assets/styles/away-element-ui-disabled.scss' // away css

export default {
  name: "Detailproductoutbound",
  dicts: ["aw_quality_shippinginspection_status"],
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
      // 产品出库详单表格数据
      detailproductoutboundList: [],
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
        deliveryNoteID: null,
        productID: null,
        productname: null,
        shippingInspectionID: null,
        customname: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        deliveryNoteID: [
          { required: true, message: "出库单编号不能为空", trigger: "blur" },
        ],
        productID: [
          { required: true, message: "产品图号不能为空", trigger: "blur" },
        ],
        shippingInspectionID: [
          { required: true, message: "出货检验编号不能为空", trigger: "blur" },
        ],
        receiptQuantity: [
          { required: true, message: "出库数量不能为空", trigger: "blur" },
        ],
        contractID: [
          { required: true, message: "客户编号不能为空", trigger: "blur" },
        ],
      },
      // 当前选中出货单id
      deliveryNoteID: null,
      // 当前选中出库单详情
      deliveryNote: {},
      // 产品列表
      productlist: [],
      // 客户信息列表
      customlist: [],
      // 出货检验单列表
      shippinginspectionlist: [],
    };
  },
  created() {
    this.getExist();
  },
  methods: {
    async getExist() {
      this.deliveryNoteID = this.$route.query.id;
      this.queryParams.deliveryNoteID = this.deliveryNoteID;
      console.log()
      this.getList();
    },
    /** 查询产品出库详单列表 */
    getList() {
      this.loading = true;
      listDetailproductoutbound(this.queryParams).then((response) => {
        this.detailproductoutboundList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询产品列表 */
    getListproduct() {
      this.loading = true;
      listProduct().then((response) => {
        this.productlist = response.rows;
        this.loading = false;
      });
    },
    /** 查询客户信息 */
    getListcustom() {
      this.loading = true;
      listCustom().then((response) => {
        this.customlist = response.rows;
        this.loading = false;
      });
    },
    /** 查询出货检验单 */
    getListshippinginspection() {
      this.loading = true;
      listShippinginspection({productID:this.form.productID}).then((response) => {
        this.shippinginspectionlist = response.rows;
        this.loading = false;
      });
    },
    /** 自动回显产品信息 */
    setDeliveryNoteOfProduct(id) {
      getProduct(id).then((response) => {
        this.deliveryNote["productname"] = response.data.name;
      });
    },
    /** 自动回显客户信息 */
    setDeliveryNoteOfCustom(id) {
      getCustom(id).then((response) => {
        this.deliveryNote["customname"] = response.data.name;
      });
    },
    /** 自动回显出货检验单信息 */
    setDeliveryNoteOfShippinginspection(id) {
      getShippinginspection(id).then((response) => {

        let data = response.data;
        let value = {
          shipmentQuantity: data.shipmentQuantity,
          detectionQuantity: data.detectionQuantity,
          qualifiedQuantity: data.qualifiedQuantity,
          unqualifiedQuantity: data.unqualifiedQuantity,
          testResult: data.testResult,
          testDate: data.testDate,
          testingPersonnel: data.testingPersonnel,
        };
        let deliveryNote = this.deliveryNote;
        this.deliveryNote = { ...deliveryNote,...value };
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
        deliveryNoteID: this.$route.query.id,
        productID: null,
        shippingInspectionID: null,
        receiptQuantity: null,
        contractID: null,
        notes: null,
      };
      this.deliveryNote = {}
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
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    handleView(row) {
      this.reset();
      this.isadd = false;
      const id = row.id || this.ids;
      getProductoutbound(id).then((response) => {
        this.form = response.data;
        getDetailproductoutbound(id).then((response) => {
          this.deliveryNote = response.data;
        });
        this.view_open = true;
        this.open = true;
        this.title = "查看产品出库详单";
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.view_open = false;
      this.isadd = true;
      this.open = true;
      this.title = "添加产品出库详单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.isadd = false;
      const id = row.id || this.ids;
      getProductoutbound(id).then((response) => {
        this.form = response.data;
        getDetailproductoutbound(id).then((response) => {
          this.deliveryNote = response.data;
        });
        this.view_open = false;
        this.open = true;
        this.title = "修改产品出库详单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (!this.isadd) {
            updateProductoutbound(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProductoutbound(this.form).then((response) => {
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
      this.$modal
        .confirm('是否确认删除产品出库详单编号为"' + ids + '"的数据项？')
        .then(function () {
          return delProductoutbound(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "produce/detailproductoutbound/export",
        {
          ...this.queryParams,
        },
        `detailproductoutbound_${new Date().getTime()}.xlsx`
      );
    },
  },
  watch: {
    "$route.query.id": {
      immediate: true,
      handler() {
        this.getExist();
      },
    },
  },
};
</script>
