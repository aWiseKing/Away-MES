
   <template>
     <div class="pdf_wrap">
        <div class="pdf_list">
        <!-- src:pdf地址，page: 当前显示页 -->
        <pdf v-for="i in numPages" :key="i" :src="src" :page="i" style="width: 100%" > </pdf>
        </div>
     <div class="close" @click="closePdf">
        关闭
     </div>
    </div>
   </template>
   
   <script>
   export default {
   import pdf from 'vue-pdf';
   export default {
    components: {
     pdf,
    },
    props: {
        pdfurl: {
            type: String,
        },
    },
    data () {
     return {
      src: '',
      numPages: undefined,
     
     }
    },
    mounted () {
        this.loadPdf(this.pdfurl)
    },
    methods: {
        loadPdf (url) {
            this.src = pdf.createLoadingTask(url)
            this.src.promise.then(pdf => {
                this.numPages = pdf.numPages // 这里拿到当前pdf总页数
            })
        },
        closePdf() {
            this.$emit('closePdf')
        }
    }
   }
   }
   </script>
   
   <style>
   .pdf_wrap {
     background: #fff;
     height: 100vh;
     position: relative;
    }
    .pdf_list {
     height: 100vh;
     overflow: scroll;
    }
    .close {
        position: absolute;
        left: 0;
        bottom: 0;
        height: 40px;
        width: 100%;
        line-height: 40px;
        text-align: center;
        color:#367CFD;
        font-size:12px;
        background: #fff;
    }
   </style>