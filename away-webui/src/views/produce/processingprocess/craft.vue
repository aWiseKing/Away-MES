<template>
  <div class="app-container">
    <div class="mainbox">
      <div class="dialog">
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="生产任务编号" prop="productionTasksID">
            {{ this.productionTasksID }}
          </el-form-item>
          <el-form-item label="加工工艺编号" prop="id">
            <el-input v-model="form.id" placeholder="请输入加工工艺编号" />
          </el-form-item>
          <el-form-item label="创建人" prop="founder">
            <el-input v-model="form.founder" placeholder="请输入创建人" />
          </el-form-item>
          <el-form-item label="下料尺寸" prop="cuttingSize">
            <el-input v-model="form.cuttingSize" placeholder="请输入下料尺寸" />
          </el-form-item>
          <el-form-item label="可制件数" prop="numberProducibleParts">
            <el-input v-model="form.numberProducibleParts" placeholder="请输入可制件数" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { listProcessingtechnology, getProcessingtechnology, delProcessingtechnology, addProcessingtechnology, updateProcessingtechnology } from "@/api/produce/processingtechnology";

export default {
  name: "Craft",
  props: ["productionTasksID"],
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
      // 加工工艺信息表格数据
      processingtechnologyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        productionTasksID: this.productionTasksID,
        createTime: null,
        founder: null,
        cuttingSize: null,
        numberProducibleParts: null
      },
      // 表单参数
      form: {
        id: null,
        productionTasksID: this.productionTasksID,
        createTime: null,
        founder: null,
        cuttingSize: null,
        numberProducibleParts: null
      },
      // 表单校验
      rules: {
        id: { required: true, message: "任务工序单编号不能为空", trigger: "blur" },
        productionTasksID: [
          { required: true, message: "生产任务编号不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建日期不能为空", trigger: "blur" }
        ],
        founder: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询加工工艺信息列表 */
    getList() {
      this.loading = true;
      listProcessingtechnology(this.queryParams).then(response => {
        this.processingtechnologyList = response.rows;
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
        productionTasksID: this.productionTasksID,
        createTime: null,
        founder: null,
        cuttingSize: null,
        numberProducibleParts: null
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
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加加工工艺信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProcessingtechnology(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改加工工艺信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
            addProcessingtechnology(this.form).then(response => {
              this.$modal.msgSuccess("创建成功");
              this.open = false;
              this.$parent.getProcessingtechnologyExist();
            });
          }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除加工工艺信息编号为"' + ids + '"的数据项？').then(function () {
        return delProcessingtechnology(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('produce/processingtechnology/export', {
        ...this.queryParams
      }, `processingtechnology_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

<style scoped>
.mainbox {
  display: flex;
  justify-content: center;
  align-items: center;
  width:100%;
  height: 100%;
}
.dialog{
  width: 420px;
  background: rgba(2555,255,255,1);
  padding: 12px 12px 12px 12px;
  border-radius: 12px;
  margin-top: 200px;
  box-shadow: 0px 0px 4px rgb(160, 160, 160);
}
.dialog-footer{
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width:100%;
}
</style>
