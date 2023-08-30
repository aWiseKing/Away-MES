<template>
  <el-main>
    <!-- 生产任务 -->
    <el-divider>生产任务</el-divider>
    <div v-if="productiontasks_list.length<1" style="display: flex;justify-content: center;align-items: center;">
      <el-button type="primary" size="mini" icon="el-icon-plus" @click="handleAddProductiontasks()"></el-button>
    </div>
    <el-table
      :data = "productiontasks_list"
      max-height="300px"
      v-if="productiontasks_list.length>0"
    >
      <el-table-column label="序号" align="center">
        <template slot-scope="scope">
          {{ scope.row.serialNum = scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="销售订单" align="center">
        <template slot-scope="scope">
          <el-select v-model="scope.row.saleOrderID" placeholder="请选择销售订单" filterable>
            <el-option
              v-for="item in saleorder_list"
              :key="item.id"
              :label="item.customername+'的'+item.id"
              :value = "item.id"
            >
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="外协" align="center">
        <template slot-scope="scope">
          <el-radio-group v-model="scope.row.outsourced">
            <el-radio v-for="item in isoutsourced" :key="item.key" :label="item.key">{{ item.value }}</el-radio>
          </el-radio-group>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          <el-select v-model="scope.row.status">
            <el-option
              v-for="item,index in state_options"
              :key="index"
              :label="item.value"
              :value="item.key"
            ></el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center">
        <template slot-scope="scope">
          <el-input v-model="scope.row.notes" placeholder="请输入备注" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button v-if="scope.row.id != null" type="text" size="mini" icon="el-icon-view" @click="jumpDetailPage(scope.row)">详细</el-button>
          <el-button type="text" size="mini" icon="el-icon-plus" @click="handleAddProductiontasks()">新增</el-button>
          <el-button type="text" size="mini" icon="el-icon-minus" @click="handleRemoveProductiontasks(scope.$index,scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-divider></el-divider>
    <!-- 生产任务 end -->
  </el-main>
</template>

<script>
import { listSaleorder } from "@/api/order/saleorder";
import { listProductiontasks, delProductiontasks, addProductiontasks, updateProductiontasks } from "@/api/produce/productiontasks";
import { listProcessingtechnology, getProcessingtechnology, delProcessingtechnology, addProcessingtechnology, updateProcessingtechnology } from "@/api/produce/processingtechnology";

export default {
  name:"ProductiontasksDialog",
  props:["proformid"],
  data(){
    return {
      // 任务单状态
      state_options: [
        { key: "0", value: "未发布" },
        { key: "1", value: "发布" },
        { key: "2", value: "暂停" },
        { key: "3", value: "完成" },
        { key: "4", value: "取消" }
      ],
      // 是否外协
      isoutsourced:[
        {key:"0",value:"否"},
        {key:"1",value:"是"}
      ],
      // 生产任务列表
      productiontasks_list: [],
      // 删除任务队列
      del_productiontasks_list:[],
      // 销售订单表单
      saleorder_list: [],
      // 生成任务单ID
      productiontasksFormID:null,
      // 初始化produceformid参数
      produceformid:this.proformid
    }
  },
  created(){
    this.loading = true;
    this.reset();
    this.getListSaleorder();
    this.getListProductiontasks(this.proformid);
    this.loading = false;
  },
  methods:{
    /** 查询销售订单列表 */
    getListSaleorder(){
      this.loading = true;
      listSaleorder({status:1}).then(response => {
        this.saleorder_list = response.rows;
        this.loading = false;
      })
    },
    /** 跳转详情页面 */
    async jumpDetailPage(row){
      let id = row.id;
      this.$router.push({ name:"Processingprocess", query:{ id:id} })

    },
    /** 提交需修改或添加的任务 */
    async submitUpDateOrAddProductiontasks(){
      if(this.productiontasks_list.length>0){
          let num = 0;
          for(num in this.productiontasks_list){
            let tmp_productiontasks = this.productiontasks_list[num];
            if(tmp_productiontasks.saleOrderID==null){
              continue;
            }
            if(tmp_productiontasks.id == null){
              tmp_productiontasks.productionTasksFormID = this.produceformid
              let response = await addProductiontasks(tmp_productiontasks);
            }else{
              let response = await updateProductiontasks(tmp_productiontasks);
            }
          }
        };
    },
    /** 提交需要删除的表单 */
    async submitDelProductiontasks(){
      if(this.del_productiontasks_list.length>0){
          let num = 0;
          for(num in this.del_productiontasks_list){
            await delProductiontasks(this.del_productiontasks_list[num])
          }
        }
    },
    /** 提交 */
    async submitForm(){
      await this.submitUpDateOrAddProductiontasks()
      await this.submitDelProductiontasks()
    },
    /** 新增生产任务表单 */
    handleAddProductiontasks(){
      let productiontasks_form =         {
          id: null,
          productionTasksFormID: null,
          serialNum: null,
          saleOrderID: null,
          outsourced: "0",
          status: "0",
          notes: null
        };
      this.productiontasks_list.push(productiontasks_form);
    },
    /** 移除生产任务表单 */
    handleRemoveProductiontasks(index,item){
      this.productiontasks_list.splice(index,1);
      if(item.id != null){
        this.del_productiontasks_list.push(item.id);
      }

    },
    /** 获取生成任务单对应任务 */
    getListProductiontasks(id){
      if(id == null){}
      else{
        listProductiontasks({productionTasksFormID:id}).then(response=>{
          this.productiontasks_list = response.rows;
        })
      }
    },
    /** 重置任务列表 */
    reset(){
      this.productiontasks_list = []
    },
  },
  watch:{
    produceformid(newval,oldval){
      if(newval != null){
        this.reset();
        this.getListProductiontasks(newval);
      }else{
        this.reset();
      }
    }
  }
}
</script>

<style>

</style>
