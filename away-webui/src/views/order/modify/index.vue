<template>
  <div  class="app-container">
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <el-row :gutter="0" type="flex" justify="center" align="middle" >
        <el-col :span="4" :offset="10" >
          <div style="font-size: 32px;">
            销售订单
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="创建日期" prop="field115">
            <el-input v-model="formData.field115" placeholder="请输入创建表单日期" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="创建人" prop="field113">
            <el-input v-model="formData.field113" placeholder="请输入创建人" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0" type="flex" justify="center" >
        <el-col :span="4" >
          <div style="font-size: 24px;">
            合同信息
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="订单编号" prop="field116">
            <el-input v-model="formData.field116" placeholder="请输入当前订单编号" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="下单日期" prop="field117">
            <el-input v-model="formData.field117" placeholder="请输入下单日期" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="客户编号" prop="field166">
            <el-select v-model="formData.field166" placeholder="请选择客户编号" filterable clearable
              :style="{width: '100%'}">
              <el-option v-for="(item, index) in field166Options" :key="index" :label="item.label"
                :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="客户名称" prop="field165">
            <el-input v-model="formData.field165" placeholder="请输入客户名称" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="产品图号" prop="field129">
            <el-input v-model="formData.field129" placeholder="请输入产品图号" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="产品名称" prop="field130">
            <el-input v-model="formData.field130" placeholder="请输入产品名称" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-form-item label="图纸附件" prop="field136">
          <el-upload ref="field136" :file-list="field136fileList" :action="field136Action" multiple
            :before-upload="field136BeforeUpload" list-type="picture" accept="image/*">
            <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传不超过 4MB 的image/*文件</div>
          </el-upload>
        </el-form-item>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="单行文本" prop="field125">
            <el-input v-model="formData.field125" placeholder="请输入单行文本" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="单行文本" prop="field126">
            <el-input v-model="formData.field126" placeholder="请输入单行文本" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="客供材料" prop="field155">
            <el-radio-group v-model="formData.field155" size="medium">
              <el-radio v-for="(item, index) in field155Options" :key="index" :label="item.value"
                :disabled="item.disabled">{{item.label}}</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label-width="110px" label="材料入库编号" prop="field158">
            <el-input v-model="formData.field158" placeholder="请输入材料入库编号" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="合同金额" prop="field122">
            <el-input v-model="formData.field122" placeholder="请输入合同金额" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="合同编号" prop="field123">
            <el-input v-model="formData.field123" placeholder="请输入合同编号" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-form-item label="合同附件" prop="field162">
          <el-upload ref="field162" :file-list="field162fileList" :action="field162Action" multiple
            :before-upload="field162BeforeUpload">
            <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传不超过 20MB 的文件</div>
          </el-upload>
        </el-form-item>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="发票类型" prop="field168">
            <el-input v-model="formData.field168" placeholder="请输入发票类型" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="发票编号" prop="field169">
            <el-input v-model="formData.field169" placeholder="请输入发票编号" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="开票日期" prop="field171">
            <el-input v-model="formData.field171" placeholder="请输入开票日期" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="开票数量" prop="field172">
            <el-input v-model="formData.field172" placeholder="请输入开票数量" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="税率" prop="field174">
            <el-input v-model="formData.field174" placeholder="请输入税率" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="税费" prop="field175">
            <el-input v-model="formData.field175" placeholder="请输入税费" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label-width="140px" label="销售单价（不含税）" prop="field179">
            <el-input v-model="formData.field179" placeholder="请输入销售单价（不含税）" clearable
              :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label-width="140px" label="销售金额（不含税）" prop="field180">
            <el-input v-model="formData.field180" placeholder="请输入销售金额（不含税）" clearable
              :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label-width="130px" label="销售单价（含税）" prop="field182">
            <el-input v-model="formData.field182" placeholder="请输入销售单价（含税）" clearable
              :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label-width="130px" label="销售金额（含税）" prop="field183">
            <el-input v-model="formData.field183" placeholder="请输入销售金额（含税）" clearable
              :style="{width: '100%'}"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="对账日期" prop="field185">
            <el-input v-model="formData.field185" placeholder="请输入对账日期" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="客户对账人员" prop="field186">
            <el-input v-model="formData.field186" placeholder="请输入客户对账人员" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="22">
          <el-form-item label="备注信息" prop="field191">
          <el-input v-model="formData.field191" type="textarea" placeholder="请输入备注信息" :autosize="{minRows: 4, maxRows: 4}" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="15">
        <el-col :span="11">
          <el-form-item label="税率" prop="field194">
            <el-input v-model="formData.field194" placeholder="请输入税率" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="税费" prop="field195">
            <el-input v-model="formData.field195" placeholder="请输入税费" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="0" type="flex" justify="center">
        <el-form-item size="mini" style="margin-left: 0px;">
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-row>
    </el-form>
  </div>
</template>
<script>
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        field115: undefined,
        field113: undefined,
        field116: undefined,
        field117: undefined,
        field166: undefined,
        field165: undefined,
        field129: undefined,
        field130: undefined,
        field136: null,
        field125: undefined,
        field126: undefined,
        field155: undefined,
        field158: undefined,
        field122: undefined,
        field123: undefined,
        field162: null,
        field168: undefined,
        field169: undefined,
        field171: undefined,
        field172: undefined,
        field174: undefined,
        field175: undefined,
        field179: undefined,
        field180: undefined,
        field182: undefined,
        field183: undefined,
        field185: undefined,
        field186: undefined,
        field191: undefined,
        field194: undefined,
        field195: undefined,
      },
      rules: {
        field115: [{
          required: true,
          message: '请输入创建表单日期',
          trigger: 'blur'
        }],
        field113: [{
          required: true,
          message: '请输入创建人',
          trigger: 'blur'
        }],
        field116: [{
          required: true,
          message: '请输入当前订单编号',
          trigger: 'blur'
        }],
        field117: [{
          required: true,
          message: '请输入下单日期',
          trigger: 'blur'
        }],
        field166: [{
          required: true,
          message: '请选择客户编号',
          trigger: 'change'
        }],
        field165: [{
          required: true,
          message: '请输入客户名称',
          trigger: 'blur'
        }],
        field129: [{
          required: true,
          message: '请输入产品图号',
          trigger: 'blur'
        }],
        field130: [{
          required: true,
          message: '请输入产品名称',
          trigger: 'blur'
        }],
        field125: [{
          required: true,
          message: '请输入单行文本',
          trigger: 'blur'
        }],
        field126: [{
          required: true,
          message: '请输入单行文本',
          trigger: 'blur'
        }],
        field155: [],
        field158: [],
        field122: [],
        field123: [],
        field168: [],
        field169: [],
        field171: [],
        field172: [],
        field174: [],
        field175: [],
        field179: [],
        field180: [],
        field182: [],
        field183: [],
        field185: [],
        field186: [],
        field191: [],
        field194: [],
        field195: [],
      },
      field136Action: 'https://jsonplaceholder.typicode.com/posts/',
      field136fileList: [],
      field162Action: 'https://jsonplaceholder.typicode.com/posts/',
      field162fileList: [],
      field166Options: [{
        "label": "选项一",
        "value": 1
      }, {
        "label": "选项二",
        "value": 2
      }],
      field155Options: [{
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
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
    field136BeforeUpload(file) {
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
    field162BeforeUpload(file) {
      let isRightSize = file.size / 1024 / 1024 < 20
      if (!isRightSize) {
        this.$message.error('文件大小超过 20MB')
      }
      return isRightSize
    },
  }
}

</script>
<style>
.el-upload__tip {
  line-height: 1.2;
}

</style>
