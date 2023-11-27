<template>
  <footer class="site-footer">
    <el-container>
      <el-row>
        <el-col :span="24">
          <div class="footer-content">
            <p>© 2023 Your Company. All rights reserved.</p>
            <p>123 Main Street, City, Country</p>
            <el-menu mode="horizontal" theme="light" class="footer-menu">
              <el-menu-item index="home">Home</el-menu-item>
              <el-menu-item index="about">About Us</el-menu-item>
              <el-menu-item index="services">Services</el-menu-item>
              <el-menu-item index="contact">Contact</el-menu-item>
            </el-menu>
            <div class="social-media">
              <el-tooltip content="Follow us on Facebook" placement="top">
                <el-link icon="el-icon-facebook" :underline="false"></el-link>
              </el-tooltip>
              <el-tooltip content="Follow us on Twitter" placement="top">
                <el-link icon="el-icon-twitter" :underline="false"></el-link>
              </el-tooltip>
              <!-- 添加其他社交媒体链接 -->
            </div>
          </div>
        </el-col>
      </el-row>
    </el-container>
  </footer>
</template>

<script>
export default {
  name: 'AppFooter',
  // 在这里可以添加一些footer的数据和逻辑
}
</script>

<style scoped>
.site-footer {
  background-color: #87CEEB; /* 天蓝色 */
  color: #fff;
  padding: 20px 0;
  text-align: center;
}

.footer-content {
  max-width: 800px;
  margin: 0 auto;
}

.footer-menu {
  margin-top: 10px;
}

.social-media el-link {
  margin-left: 10px;
}
</style>

