<template>
  <div  class="app-container">
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <el-row :gutter="0" type="flex" justify="center" align="middle" style="height: 80px;">
        <el-col :span="2" >
          <div style="font-weight: 700;font-size: 32px;">
            销售订单
          </div>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="创建日期" prop="createDate">
            <el-input v-model="formData.createDate" placeholder="请输入创建表单日期" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="创建人" prop="founder">
            <el-input v-model="formData.founder" placeholder="请输入创建人" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <!-- 合同信息 -->
      <el-divider content-position="left"><div style="font-weight: 700;font-size: 18px;">合同信息</div></el-divider>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="订单编号" prop="orderNumber">
            <el-input v-model="formData.orderNumber" placeholder="请输入当前订单编号" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="下单日期" prop="orderDate">
            <el-input v-model="formData.orderDate" placeholder="请输入下单日期" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="客户编号" prop="customerNumber">
            <el-select v-model="formData.customerNumber" placeholder="请选择客户编号" filterable clearable
              :style="{width: '100%'}">
              <el-option v-for="(item, index) in customerNumberOptions" :key="index" :label="item.label"
                :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="客户名称" prop="customerName">
            <el-input v-model="formData.customerName" placeholder="请输入客户名称" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="产品图号" prop="productDawingNumber">
            <el-input v-model="formData.productDawingNumber" placeholder="请输入产品图号" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="产品名称" prop="productName">
            <el-input v-model="formData.productName" placeholder="请输入产品名称" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-form-item label="图纸附件" prop="drawingAttachments">
          <el-upload ref="drawingAttachments" :file-list="drawingAttachmentsfileList" :action="drawingAttachmentsAction" multiple
            :before-upload="drawingAttachmentsBeforeUpload" list-type="picture" accept="image/*">
            <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传不超过 4MB 的image/*文件</div>
          </el-upload>
        </el-form-item>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="需求数量" prop="quantityDemanded">
            <el-input v-model="formData.quantityDemanded" placeholder="请输入需求数量" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="要求交期" prop="requiredDeliveryTime">
            <el-input v-model="formData.requiredDeliveryTime" placeholder="请输入要求交期" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="客供材料" prop="customerProvidedMaterials">
            <el-radio-group v-model="formData.customerProvidedMaterials" size="medium">
              <el-radio v-for="(item, index) in customerProvidedMaterialsOptions" :key="index" :label="item.value"
                :disabled="item.disabled">{{item.label}}</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label-width="110px" label="材料入库编号" prop="materialWarehousingNumber">
            <el-input v-model="formData.materialWarehousingNumber" placeholder="请输入材料入库编号" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="合同金额" prop="contractAmount">
            <el-input v-model="formData.contractAmount" placeholder="请输入合同金额" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="合同编号" prop="contractNumber">
            <el-input v-model="formData.contractNumber" placeholder="请输入合同编号" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-form-item label="合同附件" prop="contractAttachments">
          <el-upload ref="contractAttachments" :file-list="contractAttachmentsfileList" :action="contractAttachmentsAction" multiple
            :before-upload="contractAttachmentsBeforeUpload">
            <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传不超过 20MB 的文件</div>
          </el-upload>
        </el-form-item>
      </el-row>
      <el-divider content-position="left"><div style="font-weight: 700;font-size: 18px;">发票信息</div></el-divider>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="发票类型" prop="invoiceType">
            <el-input v-model="formData.invoiceType" placeholder="请输入发票类型" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="发票编号" prop="invoiceNumber">
            <el-input v-model="formData.invoiceNumber" placeholder="请输入发票编号" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="开票日期" prop="invoicingDate">
            <el-input v-model="formData.invoicingDate" placeholder="请输入开票日期" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="开票数量" prop="invoicedQuantity">
            <el-input v-model="formData.invoicedQuantity" placeholder="请输入开票数量" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="税率" prop="taxRate">
            <el-input v-model="formData.taxRate" placeholder="请输入税率" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="税费" prop="taxation">
            <el-input v-model="formData.taxation" placeholder="请输入税费" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label-width="140px" label="销售单价（不含税）" prop="salesUnitPriceExcludingTax">
            <el-input v-model="formData.salesUnitPriceExcludingTax" placeholder="请输入销售单价（不含税）" clearable
              :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label-width="140px" label="销售金额（不含税）" prop="salesAmountExcludingTax">
            <el-input v-model="formData.salesAmountExcludingTax" placeholder="请输入销售金额（不含税）" clearable
              :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label-width="130px" label="销售单价（含税）" prop="salesUnitPriceIncludingTax">
            <el-input v-model="formData.salesUnitPriceIncludingTax" placeholder="请输入销售单价（含税）" clearable
              :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label-width="130px" label="销售金额（含税）" prop="salesAmountIncludingTax">
            <el-input v-model="formData.salesAmountIncludingTax" placeholder="请输入销售金额（含税）" clearable
              :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="对账日期" prop="reconciliationDate">
            <el-input v-model="formData.reconciliationDate" placeholder="请输入对账日期" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="客户对账人员" prop="customerReconciliationPersonnel">
            <el-input v-model="formData.customerReconciliationPersonnel" placeholder="请输入客户对账人员" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="22">
          <el-form-item label="备注信息" prop="notes">
          <el-input v-model="formData.notes" type="textarea" placeholder="请输入备注信息" :autosize="{minRows: 4, maxRows: 4}" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        </el-col>
      </el-row>
      <el-divider content-position="left"><div style="font-weight: 700;font-size: 18px;">自定义附加内容</div></el-divider>
      <el-row :gutter="15" v-if="formData.customAttachment.length < 1" >
        <el-col :span="11">
          <el-button type="primary" icon="el-icon-plus" @click="customAttachmentAdd()"></el-button>
        </el-col>
      </el-row>
      <el-row :gutter="15" v-for="(item, index) in formData.customAttachment" :key="index">
        <el-col :span="10">
          <el-form-item label="字段名" :prop="'keyname'+index">
            <el-input v-model="item.keyname" placeholder="请输入字段名" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="字段值" :prop="'keyvalue'+index">
            <el-input v-model="item.keyvalue" placeholder="请输入字段值" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="1">
          <el-button type="primary" icon="el-icon-plus" @click="customAttachmentAdd()"></el-button>
        </el-col>
        <el-col :span="1">
            <el-popover
              placement="top"
              width="160"
              trigger="click"
              v-model="item.popoverVisible">
              <p>确定删除本行吗？</p>
              <div style="text-align: right; margin: 0">
                <el-button size="mini" type="text" @click="item.popoverVisible = false">取消</el-button>
                <el-button type="primary" size="mini" @click="item.popoverVisible = false;customAttachmentReduce(index)">确定</el-button>
              </div>
              <el-button slot="reference" type="primary" icon="el-icon-minus" ></el-button>
          </el-popover>
        </el-col>
      </el-row>
      <el-row :gutter="0" type="flex" justify="center">
        <el-col :span="7">
          <el-form-item size="mini" style="margin-left: 0px;">
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <!-- 删除字段行 弹窗 -->
    <el-dialog></el-dialog>
  </div>
</template>
<script>
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        createDate: undefined,
        founder: undefined,
        orderNumber: undefined,
        orderDate: undefined,
        customerNumber: undefined,
        customerName: undefined,
        productDawingNumber: undefined,
        productName: undefined,
        drawingAttachments: null,
        quantityDemanded: undefined,
        requiredDeliveryTime: undefined,
        customerProvidedMaterials: undefined,
        materialWarehousingNumber: undefined,
        contractAmount: undefined,
        contractNumber: undefined,
        contractAttachments: null,
        invoiceType: undefined,
        invoiceNumber: undefined,
        invoicingDate: undefined,
        invoicedQuantity: undefined,
        taxRate: undefined,
        taxation: undefined,
        salesUnitPriceExcludingTax: undefined,
        salesAmountExcludingTax: undefined,
        salesUnitPriceIncludingTax: undefined,
        salesAmountIncludingTax: undefined,
        reconciliationDate: undefined,
        customerReconciliationPersonnel: undefined,
        notes: undefined,
        customAttachment:[
        ]

      },
      rules: {
        createDate: [{
          required: true,
          message: '请输入创建表单日期',
          trigger: 'blur'
        }],
        founder: [{
          required: true,
          message: '请输入创建人',
          trigger: 'blur'
        }],
        orderNumber: [{
          required: true,
          message: '请输入当前订单编号',
          trigger: 'blur'
        }],
        orderDate: [{
          required: true,
          message: '请输入下单日期',
          trigger: 'blur'
        }],
        customerNumber: [{
          required: true,
          message: '请选择客户编号',
          trigger: 'change'
        }],
        customerName: [{
          required: true,
          message: '请输入客户名称',
          trigger: 'blur'
        }],
        productDawingNumber: [{
          required: true,
          message: '请输入产品图号',
          trigger: 'blur'
        }],
        productName: [{
          required: true,
          message: '请输入产品名称',
          trigger: 'blur'
        }],
        quantityDemanded: [{
          required: true,
          message: '请输入需求数量',
          trigger: 'blur'
        }],
        requiredDeliveryTime: [{
          required: true,
          message: '请输入要求交期',
          trigger: 'blur'
        }],
        customerProvidedMaterials: [],
        materialWarehousingNumber: [],
        contractAmount: [],
        contractNumber: [],
        invoiceType: [],
        invoiceNumber: [],
        invoicingDate: [],
        invoicedQuantity: [],
        taxRate: [],
        taxation: [],
        salesUnitPriceExcludingTax: [],
        salesAmountExcludingTax: [],
        salesUnitPriceIncludingTax: [],
        salesAmountIncludingTax: [],
        reconciliationDate: [],
        customerReconciliationPersonnel: [],
        notes: [],
        keyname0: [],
        keyvalue0: [],
      },
      drawingAttachmentsAction: 'https://jsonplaceholder.typicode.com/posts/',
      drawingAttachmentsfileList: [],
      contractAttachmentsAction: 'https://jsonplaceholder.typicode.com/posts/',
      contractAttachmentsfileList: [],
      customerNumberOptions: [{
        "label": "测试选项一",
        "value": 1
      }, {
        "label": "测试选项二",
        "value": 2
      }],
      customerProvidedMaterialsOptions: [{
        "label": "否",
        "value": 2
      }, {
        "label": "是",
        "value": 1
      }],
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    submitForm() {
      console.log(this.$refs['elForm']);
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
    drawingAttachmentsBeforeUpload(file) {
      let isRightSize = file.size / 1024 / 1024 < 4
      if (!isRightSize) {
        this.$message.error('文件大小超过 4MB')
      }
      let isAccept = new RegExp('image/*').test(file.type)
      if (!isAccept) {
        this.$message.error('应该选择image/*类型的文件')
      }
      return isRightSize && isAccept
    },
    contractAttachmentsBeforeUpload(file) {
      let isRightSize = file.size / 1024 / 1024 < 20
      if (!isRightSize) {
        this.$message.error('文件大小超过 20MB')
      }
      return isRightSize
    },
    // 添加自定义附件行
    customAttachmentAdd(){
      this.formData.customAttachment.push({
        keyname : undefined,
        keyvalue : undefined,
        popoverVisible:false
      })

    },
    // 删除自定义附件行
    customAttachmentReduce(index){
      this.formData.customAttachment.splice(index, 1);
    },
  }
}

</script>
<style>
.el-upload__tip {
  line-height: 1.2;
}
</style>
