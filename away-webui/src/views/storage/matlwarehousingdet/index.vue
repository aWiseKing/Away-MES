<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
    <el-row :gutter="1">
            <el-col :span="21">
              <div style="overflow-x: auto;scrollbar-width: none; white-space: nowrap;">
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
      <el-form-item label="规格类型" prop="specificationsType">
        <el-input
          v-model="queryParams.specificationsType"
          placeholder="请输入规格类型"
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
      <el-form-item label="材料密度" prop="materialDensity">
        <el-input
          v-model="queryParams.materialDensity"
          placeholder="请输入材料密度"
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
          v-hasPermi="['storage:matlwarehousingdet:add']"
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
          v-hasPermi="['storage:matlwarehousingdet:edit']"
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
          v-hasPermi="['storage:matlwarehousingdet:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['storage:matlwarehousingdet:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="matlwarehousingdetList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="入库单编号" align="center" prop="warehouseEntryID" />
      <el-table-column label="申购材料编号" align="center" prop="materialSubscription" />
      <el-table-column label="材料信息编号" align="center" prop="materialID" />
      <el-table-column label="材料名称" align="center" prop="name" />
      <el-table-column label="规格型号" align="center" prop="specificationModel" />
      <el-table-column label="发票信息编号" align="center" prop="receiptInvoiceID" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
        <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row)"
            v-hasPermi="['storage:matlwarehousingdet:edit']"
          >查看</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['storage:matlwarehousingdet:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['storage:matlwarehousingdet:remove']"
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

    <!-- 添加或修改材料入库详细对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="id" prop="id">
          <el-input v-model="form.id" placeholder="请输入id" />
        </el-form-item>
        <el-form-item label="入库单编号" prop="warehouseEntryID">
          <el-input v-model="form.warehouseEntryID" placeholder="请输入入库单编号" />
        </el-form-item>
        <el-form-item label="申购材料编号" prop="materialSubscription">
          <el-input v-model="form.materialSubscription" placeholder="请输入申购材料编号" />
        </el-form-item>
        <el-form-item label="材料信息编号" prop="materialID">
          <el-input v-model="form.materialID" placeholder="请输入材料信息编号" />
        </el-form-item>
        <el-form-item label="材料名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入材料名称" />
        </el-form-item>
        <el-form-item label="类别名称" prop="typeName">
          <el-input v-model="form.typeName" placeholder="请输入类别名称" />
        </el-form-item>
        <el-form-item label="规格类型" prop="specificationsType">
          <el-input v-model="form.specificationsType" placeholder="请输入规格类型" />
        </el-form-item>
        <el-form-item label="规格型号" prop="specificationModel">
          <el-input v-model="form.specificationModel" placeholder="请输入规格型号" />
        </el-form-item>
        <el-form-item label="材料密度" prop="materialDensity">
          <el-input v-model="form.materialDensity" placeholder="请输入材料密度" />
        </el-form-item>
        <el-form-item label="发票信息编号" prop="receiptInvoiceID">
          <el-input v-model="form.receiptInvoiceID" placeholder="请输入发票信息编号" />
        </el-form-item>
        <el-form-item label="发票类型" prop="invoiceType">
          <el-input v-model="form.invoiceType" placeholder="请输入发票类型" />
        </el-form-item>
        <el-form-item label="发票税率" prop="invoiceTaxRate">
          <el-input v-model="form.invoiceTaxRate" placeholder="请输入发票税率" />
        </el-form-item>
        <el-form-item label="不含税采购单价" prop="purchaseUnitPriceExcludingTax">
          <el-input v-model="form.purchaseUnitPriceExcludingTax" placeholder="请输入不含税采购单价" />
        </el-form-item>
        <el-form-item label="含税采购单价" prop="purchaseUnitPriceIncludingTax">
          <el-input v-model="form.purchaseUnitPriceIncludingTax" placeholder="请输入含税采购单价" />
        </el-form-item>
        <el-form-item label="入库数量" prop="receiptQuantity">
          <el-input v-model="form.receiptQuantity" placeholder="请输入入库数量" />
        </el-form-item>
        <el-form-item label="附样" prop="sampleURL">
          <image-upload v-model="form.sampleURL"/>
        </el-form-item>
        <el-form-item label="备注" prop="notes">
          <el-input v-model="form.notes" placeholder="请输入备注" />
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
import { listMatlwarehousingdet, getMatlwarehousingdet, delMatlwarehousingdet, addMatlwarehousingdet, updateMatlwarehousingdet } from "@/api/storage/matlwarehousingdet";

export default {
  name: "Matlwarehousingdet",
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
      // 材料入库详细表格数据
      matlwarehousingdetList: [],
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
        specificationsType: null,
        specificationModel: null,
        materialDensity: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        id: [
          { required: true, message: "id不能为空", trigger: "blur" }
        ],
        warehouseEntryID: [
          { required: true, message: "入库单编号不能为空", trigger: "blur" }
        ],
        materialID: [
          { required: true, message: "材料信息编号不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "材料名称不能为空", trigger: "blur" }
        ],
        materialDensity: [
          { required: true, message: "材料密度不能为空", trigger: "blur" }
        ],
        receiptInvoiceID: [
          { required: true, message: "发票信息编号不能为空", trigger: "blur" }
        ],
        purchaseUnitPriceExcludingTax: [
          { required: true, message: "不含税采购单价不能为空", trigger: "blur" }
        ],
        receiptQuantity: [
          { required: true, message: "入库数量不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询材料入库详细列表 */
    getList() {
      this.loading = true;
      listMatlwarehousingdet(this.queryParams).then(response => {
        this.matlwarehousingdetList = response.rows;
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
        warehouseEntryID: null,
        materialSubscription: null,
        materialID: null,
        name: null,
        typeName: null,
        specificationsType: null,
        specificationModel: null,
        materialDensity: null,
        receiptInvoiceID: null,
        invoiceType: null,
        invoiceTaxRate: null,
        purchaseUnitPriceExcludingTax: null,
        purchaseUnitPriceIncludingTax: null,
        receiptQuantity: null,
        sampleURL: null,
        notes: null
      };
      this.form = {
        id: null,
        warehouseEntryID: null,
        materialSubscription: null,
        receiptInvoiceID: null,
        materialID: null,
        receiptQuantity: null,
        sampleURL: null,
        notes: null,
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
      this.title = "添加材料入库详细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.isadd = false;
      const id = row.id || this.ids
      getMatlwarehousingdet(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改材料入库详细";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (!this.isadd) {
            updateMatlwarehousingdet(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMatlwarehousingdet(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除材料入库详细编号为"' + ids + '"的数据项？').then(function() {
        return delMatlwarehousingdet(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('storage/matlwarehousingdet/export', {
        ...this.queryParams
      }, `matlwarehousingdet_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
