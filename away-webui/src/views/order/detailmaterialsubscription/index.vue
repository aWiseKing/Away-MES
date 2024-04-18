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
            <el-form-item label="材料名称" prop="name">
              <el-input
                v-model="queryParams.name"
                placeholder="请输入材料名称"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="类别名称" prop="typeName">
              <el-input
                v-model="queryParams.typeName"
                placeholder="请输入类别名称"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="规格型号" prop="specificationModel">
              <el-input
                v-model="queryParams.specificationModel"
                placeholder="请输入规格型号"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="工艺编号" prop="processingTechnologyID">
              <el-input
                v-model="queryParams.processingTechnologyID"
                placeholder="请输入工艺编号"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="需用日期" prop="requiredDate">
              <el-date-picker
                clearable
                v-model="queryParams.requiredDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择需用日期"
              >
              </el-date-picker>
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
          v-if="purchaserequisition.status == '0'"
          @click="handleAdd"
          v-hasPermi="['order:detailmaterialsubscription:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          v-if="purchaserequisition.status == '0'"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['order:detailmaterialsubscription:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          v-if="purchaserequisition.status == '0'"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['order:detailmaterialsubscription:remove']"
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
          v-hasPermi="['order:detailmaterialsubscription:export']"
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
      :data="detailmaterialsubscriptionList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="申购材料编号"
        align="center"
        prop="materialSubscription"
      />
      <el-table-column label="申购单编号" align="center" prop="subscribeID" />
      <el-table-column
        label="任务编号"
        align="center"
        prop="productionTasksID"
      />
      <el-table-column label="材料名称" align="center" prop="name" />
      <el-table-column label="类别名称" align="center" prop="typeName" />
      <el-table-column
        label="规格型号"
        align="center"
        prop="specificationModel"
      />
      <el-table-column
        label="工艺编号"
        align="center"
        prop="processingTechnologyID"
      />
      <el-table-column
        label="申购数量"
        align="center"
        prop="subscriptionQuantity"
      />
      <el-table-column
        label="需用日期"
        align="center"
        prop="requiredDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.requiredDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
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
            v-hasPermi="['order:detailmaterialsubscription:edit']"
            >查看</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            v-if="purchaserequisition.status == '0'"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['order:detailmaterialsubscription:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            v-if="purchaserequisition.status == '0'"
            @click="handleDelete(scope.row)"
            v-hasPermi="['order:detailmaterialsubscription:remove']"
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

    <!-- 添加或修改申购材料详细对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <div
        style="
          width: 100%;
          height: 600px;
          overflow-y: scroll;
          overflow-x: hidden;
        "
      >
        <el-form
          ref="form"
          :model="form"
          :disabled="view_open"
          :rules="rules"
          label-width="80px"
        >
          <el-row :gutter="12"
            ><el-col :span="12"
              ><el-form-item label="申购单编号" prop="subscribeID">
                <el-input
                  v-model="form.subscribeID"
                  disabled
                  placeholder="请输入申购单编号"
                /> </el-form-item></el-col
          ></el-row>

          <el-row :gutter="12"
            ><el-col :span="12">
              <el-form-item label="申购材料编号" prop="materialSubscription">
                <el-input
                  v-model="form.materialSubscription"
                  placeholder="请输入申购材料编号"
                /> </el-form-item></el-col
          ></el-row>
          <el-row :gutter="12"
            ><el-col :span="12">
              <el-form-item label="任务单" prop="productionTasksFormID">
                <el-select
                  v-model="productionTasksFormID"
                  placeholder="请选择任务单"
                  @focus="getListproductiontasklist()"
                    filterable
                >
                  <el-option
                    v-for="(item, index) in productiontasklistlist"
                    :key="index"
                    :label="item.referred"
                    :value="item.id"
                    @click.native="setProductiontasklist(item.id)"
                  >
                  </el-option>
                </el-select> </el-form-item></el-col
          ></el-row>
          <el-row :gutter="12"
            ><el-col :span="12">
              <el-form-item label="任务编号" prop="productionTasksID">
                <el-select
                  filterable
                  v-model="form.productionTasksID"
                  :disabled="productiontasklist.id == null"
                  placeholder="请选择任务编号"
                  @focus="
                    getListproductiontasks(
                      productiontasklist.id
                    )
                  "
                >
                  <el-option
                    v-for="(item, index) in productiontaskslist"
                    :key="index"
                    :label="item.name"
                    :value="item.id"
                    @click.native="setProductiontasks(item.id)"
                  >
                  </el-option>
                </el-select> </el-form-item></el-col
            ><el-col :span="12">
              <el-form-item label="工艺编号" prop="processingTechnologyID">
                <el-input
                  v-model="form.processingTechnologyID"
                  disabled
                  placeholder="请输入工艺编号"
                /> </el-form-item></el-col
          ></el-row>
          <el-row :gutter="12"
            ><el-col :span="12">
              <el-form-item label="材料编号" prop="materialID">
                <el-select
                  filterable
                  v-model="form.materialID"
                  placeholder="请选择材料编号"
                  :disabled="form.processingTechnologyID == null"
                  @focus="
                    getListmateriallistoftechnology(form.processingTechnologyID)
                  "
                >
                  <el-option
                    v-for="(item, index) in materiallistoftechnologylist"
                    :key="index"
                    :label="item.materialID"
                    :value="item.materialID"
                    @click.native="setMaterialListOfTechnology(item.id)"
                  >
                  </el-option>
                </el-select> </el-form-item></el-col
          >
          <el-col :span="12">
              <el-form-item label="材料价格" prop="materialDensity">
                <el-input
                  v-model="materiallistoftechnology.materialPrice"
                  placeholder="请输入材料价格"
                  disabled
                /> </el-form-item></el-col
          >

          </el-row>
          <el-row :gutter="12"
            ><el-col :span="12">
              <el-form-item label="材料名称" prop="name">
                <el-input
                  v-model="materiallistoftechnology.name"
                  placeholder="请输入材料名称"
                  disabled
                /> </el-form-item></el-col
            ><el-col :span="12">
              <el-form-item label="类别名称" prop="typeName">
                <el-input
                  v-model="materiallistoftechnology.typeName"
                  placeholder="请输入类别名称"
                  disabled
                /> </el-form-item></el-col></el-row
          ><el-row :gutter="12"
            ><el-col :span="12">
              <el-form-item label="规格类型" prop="specificationsType">
                <el-input
                  v-model="materiallistoftechnology.specificationsType"
                  placeholder="请输入规格类型"
                  disabled
                /> </el-form-item></el-col
            ><el-col :span="12">
              <el-form-item label="规格型号" prop="specificationModel">
                <el-input
                  v-model="materiallistoftechnology.specificationModel"
                  placeholder="请输入规格型号"
                  disabled
                /> </el-form-item></el-col></el-row
          ><el-row :gutter="12"
            >
          </el-row>
          <el-row :gutter="12"
            ><el-col :span="12"
              ><el-form-item label="申购数量" prop="subscriptionQuantity">
                <el-input
                  v-model="form.subscriptionQuantity"
                  placeholder="请输入申购数量"
                /> </el-form-item></el-col
            ><el-col :span="12">
              <el-form-item label="需用日期" prop="requiredDate">
                <el-date-picker
                  clearable
                  v-model="form.requiredDate"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="请选择需用日期"
                >
                </el-date-picker> </el-form-item></el-col
          ></el-row>
          <el-row :gutter="12"
            ><el-col :span="24"
              ><el-form-item label="附样" prop="sampleURL">
                <image-upload
                  v-model="form.sampleURL"
                /> </el-form-item></el-col
          ></el-row>
          <el-row :gutter="12"
            ><el-col :span="24">
              <el-form-item label="备注" prop="note">
                <el-input
                  v-model="form.note"
                  placeholder="请输入备注"
                /> </el-form-item></el-col
          ></el-row>
        </el-form>
      </div>
      <div v-if="!view_open" slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listDetailmaterialsubscription,
  getDetailmaterialsubscription,
} from "@/api/order/detailmaterialsubscription";
import { getPurchaserequisition } from "@/api/order/purchaserequisition";
import {
  listProductiontasklist,
  getProductiontasklist,
} from "@/api/produce/productiontasklist.js";
import {
  listProductiontasks,
  getProductiontasks,
} from "@/api/produce/productiontasks.js";
import {
  listMaterialListOfTechnology,
  getMaterialListOfTechnology,
} from "@/api/produce/MaterialListOfTechnology.js";

import {
  getMaterialsubscription,
  updateMaterialsubscription,
  addMaterialsubscription,
  delMaterialsubscription,
} from "@/api/order/materialsubscription.js";
import "@/assets/styles/away-element-ui-disabled.scss"; // away css

export default {
  name: "Detailmaterialsubscription",
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
      // 申购材料详细表格数据
      detailmaterialsubscriptionList: [],
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
        typeName: null,
        specificationModel: null,
        processingTechnologyID: null,
        requiredDate: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        materialSubscription: [
          { required: true, message: "申购材料编号不能为空", trigger: "blur" },
        ],
        subscribeID: [
          { required: true, message: "申购单编号不能为空", trigger: "blur" },
        ],
        productionTasksID: [
          { required: true, message: "任务编号不能为空", trigger: "change" },
        ],
        materialID: [
          {
            required: true,
            message: "材料信息编号不能为空",
            trigger: "change",
          },
        ],
        processingTechnologyID: [
          { required: true, message: "工艺编号不能为空", trigger: "blur" },
        ],
        subscriptionQuantity: [
          { required: true, message: "申购数量不能为空", trigger: "blur" },
        ],
        requiredDate: [
          { required: true, message: "需用日期不能为空", trigger: "blur" },
        ],
      },
      // 当前申购单编号
      subscribeID: null,
      // 当前申购单
      purchaserequisition: {},
      // 任务单信息
      productiontasklistlist: [],
      productionTasksFormID: null,
      // 当前选中任务单
      productiontasklist: {},
      // 任务信息
      productiontaskslist: [],
      // 当前选中任务
      productiontasks: {},
      // 工艺所需材料详细信息
      materiallistoftechnologylist: [],
      // 工艺所需材料详细信息
      materiallistoftechnology: {},


    };
  },
  created() {
    this.getExist();
  },
  methods: {
    getExist() {
      this.subscribeID = this.$route.query.subscribeID;
      this.queryParams.subscribeID = this.subscribeID;

      getPurchaserequisition(this.subscribeID).then((response) => {
        this.purchaserequisition = response.data;
      });
      this.getList();
    },
    /** 查询申购材料详细列表 */
    getList() {
      this.loading = true;
      listDetailmaterialsubscription(this.queryParams).then((response) => {
        console.log(this.queryParams)
        this.detailmaterialsubscriptionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询任务单信息 */
  async  getListproductiontasklist() {
      this.loading = true;
       let total=  (await listProductiontasklist())["total"];
      listProductiontasklist({pageSize:total}).then((response) => {
        this.productiontasklistlist = response.rows;
        this.loading = false;
      });
    },
    /** 选中任务单 */
    setProductiontasklist(id) {
      this.loading = true;

      getProductiontasklist(id).then((response) => {
        this.productiontasklist = response.data;


        this.loading = false;
      });
    },
    /** 查询任务信息 */
 async   getListproductiontasks(productionTasksFormID) {

      this.loading = true;
       let total= (await listProductiontasks())["total"];
      listProductiontasks({
        productionTasksFormID: productionTasksFormID,pageSize:total
      }).then((response) => {
        this.productiontaskslist = response.rows;
        this.loading = false;
      });
    },
    /** 选中任务 */
    setProductiontasks(id) {
      this.loading = true;

      getProductiontasks(id).then((response) => {
        this.productiontasks = response.data;
        this.form.processingTechnologyID =
          this.productiontasks.processingTechnologyID;
        this.loading = false;
      });
    },
    /** 工艺所需材料详细信息 */
   async getListmateriallistoftechnology(processingTechnologyID) {
      this.loading = true;
             let total= (await listMaterialListOfTechnology())["total"];

      listMaterialListOfTechnology({
        processingTechnologyID: processingTechnologyID, pageSize:total
      }).then((response) => {
        this.materiallistoftechnologylist = response.rows;
        this.loading = false;
      });
    },
    /** 选中工艺所需材料详细信息 */
    setMaterialListOfTechnology(id) {
      this.loading = true;
      getMaterialListOfTechnology(id).then((response) => {
             this.materiallistoftechnology = response.data;
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
        materialSubscription: null,
        subscribeID: this.$route.query.subscribeID,
        productionTasksID: null,
        materialID: null,
        processingTechnologyID: null,
        subscriptionQuantity: null,
        requiredDate: null,
        sampleURL: null,
        note: null,
      };
      this.productiontasklistlist = [];
      // 当前选中任务单
      this.productiontasklist = {};
      this.productionTasksFormID = null;
      // 任务信息
      this.productiontaskslist = [];
      // 当前选中任务
      this.productiontasks = {};
      // 工艺所需材料详细信息
      this.materiallistoftechnologylist = [];
      // 工艺所需材料详细信息
      this.materiallistoftechnology = {};
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
      this.ids = selection.map((item) => item.materialSubscription);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    handleView(row) {
      this.reset();
      this.isadd = false;
      const materialSubscription = row.materialSubscription || this.ids;
      getMaterialsubscription(materialSubscription).then((response) => {
        this.form = response.data;
        this.view_open = true;
        listMaterialListOfTechnology({
          processingTechnologyID: this.form.processingTechnologyID,
          materialID: this.form.materialID,
        }).then((response) => {
          this.materiallistoftechnology = response.rows[0];
        });
        getProductiontasks(this.form.productionTasksID).then((response) => {
          this.productionTasksFormID = response.data.id;
        });
        this.open = true;
        this.title = "查看申购材料详细";
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.isadd = true;
      this.view_open = false;
      this.open = true;
      this.title = "添加申购材料详细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.isadd = false;
      const materialSubscription = row.materialSubscription || this.ids;
      getMaterialsubscription(materialSubscription).then((response) => {
        this.form = response.data;
        listMaterialListOfTechnology({
          processingTechnologyID: this.form.processingTechnologyID,
          materialID: this.form.materialID,
        }).then((response) => {
          this.materiallistoftechnology = response.rows[0];
        });
        getProductiontasks(this.form.productionTasksID).then((response) => {
          this.productionTasksFormID = response.data.id;
        });
        this.view_open = false;
        this.open = true;
        this.title = "修改申购材料详细";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (!this.isadd) {
            updateMaterialsubscription(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {

            addMaterialsubscription(this.form).then((response) => {
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
      const materialSubscriptions = row.materialSubscription || this.ids;
      this.$modal
        .confirm(
          '是否确认删除申购材料详细编号为"' +
            materialSubscriptions +
            '"的数据项？'
        )
        .then(function () {
          return delMaterialsubscription(materialSubscriptions);
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
        "order/detailmaterialsubscription/export",
        {
          ...this.queryParams,
        },
        `detailmaterialsubscription_${new Date().getTime()}.xlsx`
      );
    },
  },
  watch: {
    "$route.query.subscribeID": {
      immediate: true,
      handler() {
        this.getExist();
      },
    },
  },
};
</script>
