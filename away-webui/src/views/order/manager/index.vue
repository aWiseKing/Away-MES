<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch">
      <el-form-item label="订单名称" prop="orderName">
        <el-input
          v-model="queryParams.orderName"
          placeholder="请输入订单名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="订单状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 按钮行 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd()"
          v-hasPermi="['system:menu:add']"
        >新增</el-button>
      </el-col>
    </el-row>
    <!-- 订单 -->
    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="orderList"
      row-key="indentId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column prop="indentName" label="订单名称" :show-overflow-tooltip="true" width="160"></el-table-column>
      <el-table-column prop="status" label="状态" width="80">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button prop="status" v-if="scope.row.status==0"
            size="mini"
            type="text"
            icon="el-icon-video-play"
            @click="handleModify(scope.row)"
            v-hasPermi="['system:indent:play']"
          >发布</el-button>
          <el-button v-if="scope.row.status==1"
            size="mini"
            type="text"
            icon="el-icon-video-pause"
            @click="handleAdd(scope.row)"
            v-hasPermi="['system:indent:pause']"
          >停止</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-help"
            @click="orderStatusDialog = true"
            v-hasPermi="['system:indent:pause']"
          >状态</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:indent:edit']"
          >修改</el-button>
          <el-popover
              placement="top"
              width="160"
              trigger="click"
              v-model="scope.row.delPopVisble">
              <p>确定删除<span style="color:rgba(255,0,0,1)">{{ scope.row.indentName }}</span>吗？</p>
              <div style="text-align: right; margin: 0">
                <el-button size="mini" type="text" @click="scope.row.delPopVisble = false">取消</el-button>
                <el-button type="primary" size="mini" @click="scope.row.delPopVisble = false;handleDelete(scope.row)">确定</el-button>
              </div>
              <el-button
                slot="reference"
                size="mini"
                type="text"
                icon="el-icon-delete"
                v-hasPermi="['system:indent:remove']"
              >删除</el-button>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
    <!-- 弹窗 -->
    <el-drawer
      title="当前订单"
      :visible.sync="orderStatusDialog"
      :size="300"
    >
        <div style="width: 100%;height: 100%;display: flex;justify-content: center;">
          <div style="widows: 200px;">
            <el-steps :active="3" direction="vertical" :align-center="true" finish-status="success">
              <el-step title="已发布"></el-step>
              <el-step title="计划部确认进行中"></el-step>
              <el-step title="工艺规划中"></el-step>
              <el-step title="计划部审批中"></el-step>
              <el-step title="生产中"></el-step>
              <el-step title="质量检测中"></el-step>
              <el-step title="入库中"></el-step>
              <el-step title="出库中"></el-step>
              <el-step title="订单完成"></el-step>
            </el-steps>
          </div>
        </div>
    </el-drawer>
  </div>
</template>

<script>
export default {
  data(){
    return{
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 菜单表格树数据
      orderList: [],
      // 菜单树选项
      menuOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示订单状态
      orderStatusDialog: false,
      // 是否展开，默认全部折叠
      isExpandAll: false,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams:{
        orderName:undefined,
        visible:undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        menuName: [
          { required: true, message: "菜单名称不能为空", trigger: "blur" }
        ],
        orderNum: [
          { required: true, message: "菜单顺序不能为空", trigger: "blur" }
        ],
        path: [
          { required: true, message: "路由地址不能为空", trigger: "blur" }
        ]
      },
      // 菜单状态
      dict:{
        type:{sys_normal_disable:[
          {
            type:"1",
            lable:"正常"
          }
        ]}
      }
    }
  },
  methods:{
    // 选择图标
    selected(name) {
      this.form.icon = name;
    },
    /** 查询菜单列表 */
    getList() {
      this.loading = true;
      this.orderList=[
        {
          indentName:"测试订单1",
          status:"1",
          createTime:"2022-01-12 12:12:2",
          delPopVisble:false
        }
      ]

      this.loading = false;
    },
    // 表单重置
    reset() {
      this.form = {
        parentId: 0,
        orderName: undefined,
        visible: "0",
        status: "0"
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 新建订单
    handleAdd(){
      this.$router.push("/order/create")
    },
    // 修改订单
    handleUpdate(row){
      this.$router.push("/order/modify")
    },
    // 获取已有订单列表
    getorderList(){
      this.orderList = [
        {
          menuName:"测试",
          status:"正常",
        }
      ]
    }
  },
  mounted(){
    this.getList()
  }
}
</script>

<style>

</style>
