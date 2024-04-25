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
            <el-form-item label="订单编号" prop="saleorderID">
              <el-input
                v-model="queryParams.saleorderID"
                placeholder="请输入订单编号"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="材料基础信息编号" prop="materialID">
              <el-input
                v-model="queryParams.materialID"
                placeholder="请输入材料基础信息编号"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>

            <el-form-item label="入库数量" prop="receiptQuantity">
              <el-input
                v-model="queryParams.receiptQuantity"
                placeholder="请输入入库数量"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>

            <el-form-item label="备注" prop="notes">
              <el-input
                v-model="queryParams.notes"
                placeholder="请输入备注"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="材料名称" prop="materialName">
              <el-input
                v-model="queryParams.materialName"
                placeholder="请输入材料名称"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="材料分类" prop="typeID">
              <el-input
                v-model="queryParams.typeID"
                placeholder="请输入材料分类"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="规格类型" prop="specificationsID">
              <el-input
                v-model="queryParams.specificationsID"
                placeholder="请输入规格类型"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="材料密度" prop="materialDensity">
              <el-input
                v-model="queryParams.materialDensity"
                placeholder="请输入材料密度"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="材料价格" prop="materialPrice">
              <el-input
                v-model="queryParams.materialPrice"
                placeholder="请输入材料价格"
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
            <el-form-item label="实体姓名" prop="partnerName">
              <el-input
                v-model="queryParams.partnerName"
                placeholder="请输入实体姓名"
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
      <el-col :span="1.5" v-if="upper_status == '0'">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['storage:DetailCustomerMaterialsStorage:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5" v-if="upper_status == '0'">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['storage:DetailCustomerMaterialsStorage:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5" v-if="upper_status == '0'">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['storage:DetailCustomerMaterialsStorage:remove']"
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
          v-hasPermi="['storage:DetailCustomerMaterialsStorage:export']"
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
      :data="DetailCustomerMaterialsStorageList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单编号" align="center" prop="saleorderID" />
      <el-table-column
        label="材料基础信息编号"
        align="center"
        prop="materialID"
      />
      <el-table-column
        label="入库单编号"
        align="center"
        prop="warehouseEntryID"
      />

      <el-table-column label="材料名称" align="center" prop="materialName" />

      <el-table-column label="材料价格" align="center" prop="materialPrice" />

      <el-table-column
        label="规格型号"
        align="center"
        prop="specificationModel"
      />
      <el-table-column label="实体姓名" align="center" prop="partnerName" />

      <el-table-column label="入库数量" align="center" prop="receiptQuantity" />

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
            v-hasPermi="['storage:DetailCustomerMaterialsStorage:edit']"
            >查看</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['storage:DetailCustomerMaterialsStorage:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['storage:DetailCustomerMaterialsStorage:remove']"
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

    <!-- 添加或修改客供材料入库详细对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form
        ref="form"
        :model="form"
        :rules="rules"
        label-width="80px"
        :disabled="view_open"
      >
        <el-row :gutter="12">
          <el-col :span="12">
            <el-form-item label="入库单编号" prop="warehouseEntryID">
              <el-input
                disabled
                v-model="form.warehouseEntryID"
                placeholder="请输入入库单编号"
              />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="入库数量" prop="receiptQuantity">
              <el-input
                v-model="form.receiptQuantity"
                placeholder="请输入入库数量"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="12">
          <el-col :span="12">
            <el-form-item label="订单编号" prop="saleorderID">
              <el-select
                filterable
                v-model="form.saleorderID"
                placeholder="请选择订单信息"
                @focus="getOrderList()"
              >
                <el-option
                  v-for="(item, index) in OrderList"
                  :key="index"
                  :label="item.id"
                  :value="item.id"
                  @click.native="setOrder(item.id)"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="实体姓名">
              <el-input
                disabled
                v-model="Order.name"
                placeholder="请输入实体姓名"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="12">
          <el-col :span="12">
            <el-form-item label="材料编号" prop="materialID">
              <el-select
                filterable
                v-model="form.materialID"
                placeholder="请选材料编号"
                @focus="getBasicInformationOfMaterialsList()"
              >
                <el-option
                  v-for="(item, index) in BasicInformationOfMaterialsList"
                  :key="index"
                  :label="item.id"
                  :value="item.id"
                  @click.native="setBasicInformationOfMaterials(item.id)"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="12">
          <el-col :span="12">
            <el-form-item label="材料名称" prop="materialName">
              <el-input
                disabled
                v-model="BasicInformationOfMaterials.name"
                placeholder="请输入材料名称"
              />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="规格类型" prop="specificationsType">
              <el-input
                disabled
                v-model="BasicInformationOfMaterials.specificationsType"
                placeholder="请输入规格类型"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="12">
          <el-col :span="12">
            <el-form-item label="材料密度" prop="materialDensity">
              <el-input
                disabled
                v-model="BasicInformationOfMaterials.materialDensity"
                placeholder="请输入材料密度"
              />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="材料价格" prop="materialPrice">
              <el-input
                disabled
                v-model="BasicInformationOfMaterials.materialPrice"
                placeholder="请输入材料价格"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="12">
          <el-col :span="12">
            <el-form-item label="类别名称">
              <el-input
                disabled
                v-model="BasicInformationOfMaterials.typeName"
                placeholder="请输入类别名称"
              />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="规格型号">
              <el-input
                disabled
                v-model="BasicInformationOfMaterials.specificationModel"
                placeholder="请输入规格型号"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="12">
          <el-col :span="12">
            <el-form-item label="备注" prop="notes">
              <el-input v-model="form.notes" placeholder="请输入备注" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="12">
          <el-col :span="12">
            <el-form-item label="附样" prop="sampleURL">
              <image-upload v-model="form.sampleURL" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer" v-if="!view_open">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listDetailCustomerMaterialsStorage,
  getDetailCustomerMaterialsStorage,
} from "@/api/storage/DetailCustomerMaterialsStorage";

import {
  getCustomerMaterialsStorage,
  delCustomerMaterialsStorage,
  addCustomerMaterialsStorage,
  updateCustomerMaterialsStorage,
} from "@/api/storage/CustomerMaterialsStorage";

import {
  listOrderhoursdetailed,
  getOrderhoursdetailed,
} from "@/api/produce/orderhoursdetailed";

import {
  listBasicinformationofmaterials,
  getBasicinformationofmaterials,
} from "@/api/storage/basicinformationofmaterials";

import { fileDownload, fileUpdate } from "@/api/file/file";

import Filedown from "../../../components/FileDown/filedown.vue";

export default {
  name: "DetailCustomerMaterialsStorage",
  components: { filedown: Filedown },

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
      // 客供材料入库详细表格数据
      DetailCustomerMaterialsStorageList: [],
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
        saleorderID: null,
        materialID: null,
        warehouseEntryID: null,
        receiptQuantity: null,
        sampleURL: null,
        notes: null,
        materialName: null,
        typeID: null,
        specificationsID: null,
        materialDensity: null,
        materialPrice: null,
        typeName: null,
        specificationsType: null,
        specificationModel: null,
        partnerName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        saleorderID: [
          { required: true, message: "订单编号不能为空", trigger: "blur" },
        ],
        materialID: [
          {
            required: true,
            message: "材料基础信息编号不能为空",
            trigger: "blur",
          },
        ],
        warehouseEntryID: [
          { required: true, message: "入库单编号不能为空", trigger: "blur" },
        ],
      },
      fileList: [],
      //父单状态
      upper_status: null,
      //父单编号
      warehouseEntryID: null,
      //订单
      OrderList: [],
      //选中的订单
      Order: {},
      //材料
      BasicInformationOfMaterialsList: [],
      //选中的材料
      BasicInformationOfMaterials: {},
    };
  },
  created() {
    this.getExist();
  },
  methods: {
    /** 查询客供材料入库详细列表 */

    getExist() {
      this.warehouseEntryID = this.$route.query.id;
      this.upper_status = this.$route.query.status;
      this.queryParams.warehouseEntryID = this.warehouseEntryID;
      this.getList();
    },
    getList() {
      this.loading = true;
      listDetailCustomerMaterialsStorage(this.queryParams).then((response) => {
        this.DetailCustomerMaterialsStorageList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //拿到订单
    async getOrderList() {
      let total = (await listOrderhoursdetailed())["total"];
      listOrderhoursdetailed({
        iscustomersuppliedmaterials: 1,
        pageSize: total,
      }).then((respons) => {
        this.OrderList = respons.rows;
      });
    },
    //选择订单

    setOrder(id) {
      getOrderhoursdetailed(id).then((response) => {
        this.Order = response.data;
      });
    },

    //拿到材料
    async getBasicInformationOfMaterialsList() {
      let total = (await listBasicinformationofmaterials())["total"];
      listBasicinformationofmaterials({ pageSize: total }).then((response) => {
        this.BasicInformationOfMaterialsList = response.rows;
      });
    },
    //选择材料

    setBasicInformationOfMaterials(id) {
      getBasicinformationofmaterials(id).then((response) => {
        this.BasicInformationOfMaterials = response.data;
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
        id: null, //
        saleorderID: null, //
        materialID: null, //
        warehouseEntryID: this.$route.query.id, //
        receiptQuantity: null, //
        sampleURL: null, //
        notes: null, //
      };
      this.resetForm("form");
      this.fileList = [];
      this.OrderList = [];
      //选中的订单
      this.Order = {};
      //材料
      this.BasicInformationOfMaterialsList = [];
      //选中的材料
      this.BasicInformationOfMaterials = {};
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
      this.view_open = true;
      this.reset();
      getCustomerMaterialsStorage(row.id).then((response) => {
        this.form = response.data;

        getOrderhoursdetailed(row.saleorderID).then((response) => {
          this.Order = response.data;
        });

        getBasicinformationofmaterials(row.materialID).then((response) => {
          this.BasicInformationOfMaterials = response.data;
          console.log(response.data);
        });
      });

      this.open = true;
      this.title = "查看客供材料入库详细";
    },

    /** 文件上传 */
    async fileUpdate() {
      let file_list = this.$refs.upload.uploadFiles;
      if (file_list.length > 0) {
        let num = 0;
        let formData = new FormData();
        for (num in file_list) {
          formData.append("files", file_list[num].raw);
        }
        let response = await fileUpdate(formData);
        this.form.sampleURL = response;
        console.log("111");
      }
    },
    /** 文件下载 */
    async fileDown(file_name) {
      let tmp = await fileDownload(file_name);
      this.view_form.files.push(tmp);
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.isadd = true;
      this.view_open = false;
      this.open = true;
      this.title = "添加客供材料入库详细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.view_open = false;
      this.reset();
      this.isadd = false;
      const id = row.id || this.ids;
      getDetailCustomerMaterialsStorage(id).then((response) => {
        this.form = response.data;

        getOrderhoursdetailed(row.saleorderID).then((response) => {
          this.Order = response.data;
        });

        getBasicinformationofmaterials(row.materialID).then((response) => {
          this.BasicInformationOfMaterials = response.data;
          console.log(response.data);
        });

        this.open = true;
        this.title = "修改客供材料入库详细";
      });
    },
    /** 提交按钮 */
    async submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (!this.isadd) {
            updateCustomerMaterialsStorage(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCustomerMaterialsStorage(this.form).then((response) => {
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
        .confirm('是否确认删除客供材料入库详细编号为"' + ids + '"的数据项？')
        .then(function () {
          return delCustomerMaterialsStorage(ids);
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
        "storage/DetailCustomerMaterialsStorage/export",
        {
          ...this.queryParams,
        },
        `DetailCustomerMaterialsStorage_${new Date().getTime()}.xlsx`
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
    "this.$route.query.status": {
      immediate: true,
      handler() {
        this.getExist();
      },
    },
  },
};
</script>
