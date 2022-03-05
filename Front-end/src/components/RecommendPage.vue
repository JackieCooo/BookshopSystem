<template>
  <el-space alignment="start">
    <el-space direction="vertical" :spacer="spacer">
      <el-space v-for="i in recommendContent" :key="i" class="box" :size="20">
        <el-image :src="i.src" fit="contain" style="width: 100px; height: 200px;"></el-image>
        <div>
          <div><h3>{{i.title}}</h3></div>
          <div><p>{{i.content}}</p></div>
          <el-space>
            <el-icon class="color-gray"><clock /></el-icon>
            <p class="color-gray space">{{i.time}}</p>
            <p class="color-gray">评论者：{{i.author}}</p>
          </el-space>
        </div>
      </el-space>
    </el-space>
    <RightSideBoard title="最新资讯"></RightSideBoard>
  </el-space>
</template>

<script setup>
import {ElDivider} from "element-plus";
import {h} from "vue"

const spacer = h(ElDivider, { direction: 'horizontal' });
</script>

<script>
import {Clock} from "@element-plus/icons-vue";
import RightSideBoard from "@/components/RightSideBoard";

export default {
  name: "RecommendPage",
  components: {
    Clock,
    RightSideBoard
  },
  data() {
    return {
      recommendContent: [
        {src: require('@/assets/recommendBooks/01.jpg'), title: '为什么伏地魔战争双方的支持者，都觉得自己是正义的？', content: '自从那个大家都知道但是我在这里不能提名字（以下简称伏地魔）的战争开战以来，可能会令很多人，尤其是年轻人感到疑惑，手机和互联网的普及使得我们可以在网络上更直观地目击战争，但是我们却比以往的任何一场战争更难以看到战争的真相。 就拿前几天占据了西方媒体头版头条的受...', time: '2022-03-03', author: '李小丢'},
        {src: require('@/assets/recommendBooks/02.jpg'), title: '2022午夜文库开年第一本，为什么值得买？', content: '查资料的时候很想问，是谁编辑的这位作者的百度百科，竟然有点想认识。 《细红线》是午夜文库第一次出版的飞鸟高老师的作品。文中并没有“眼花缭乱的密室”，没有“冒险小说色彩”，想了想，似乎也没有“小巧的诡计”。 准确地说，这是一本很纯粹的社会派作品。 户塚是个职场人...', time: '2022-03-04', author: '新星出版社'},
        {src: require('@/assets/recommendBooks/03.jpg'), title: '装饰着雕像的大厅已化为废墟', content: '古希腊，狭义上指希腊地区从公元前12世纪迈锡尼文明毁灭至公元前146年希腊地区被罗马共和国征服为止。广义上指爱琴诸文明在罗马人征服前的全部历史。 本书《古希腊人》，叙述的时间从公元前1600年到公元400年，跨度接近2000年。 早在约公元前3650年，爱琴海地区就孕育了灿烂的...', time: '2022-03-05', author: '芥子书屋'},
        {src: require('@/assets/recommendBooks/04.jpg'), title: '《莱姆狂想曲》番外：猎魔行动', content: '麻省理工学院，查尔斯河畔空气新鲜，夜空繁星点点，宛若一整块色泽幽暗的珐琅。一艘飞艇静静地悬停在空中，四名克格勃阿尔法突击队的特工纵身而下，索降在一栋建筑的穹顶上...', time: '2022-03-06', author: '顾大海'},
        {src: require('@/assets/recommendBooks/05.jpg'), title: '疫情并没有影响春天到来的进程', content: '我知道我已经82岁了，但当我在工作室开始工作时，我感觉自己仿佛才30岁。 对于很多人来说，没完没了地待在工作室里工作，听起来并不是一种理想的生活方式，但在大卫·霍克尼看来，这就是完美的日常生活。他乐在其中，并且找到了自己的节奏。更可贵的是，年逾80岁的他仍在不断地...', time: '2022-03-07', author: '晓逢'},
        {src: require('@/assets/recommendBooks/06.jpg'), title: '效率脑科学：卓有成效地完成每一项工作｜对自己的精神健康负责', content: '人类社会发展到今天究竟靠什么？ 我们听命于大脑？还是大脑服从我们？ 我们如何对自己的精神健康负责？ 人类文明走到今天，是伴随生产加工不断改进，进而提高效率而不断向前发展的。 如果推理，人类进步无非是利益之争；再往前推，是智慧博弈；推到根子上，则是大脑较量。 我们...', time: '2022-03-03', author: '沧海先声'},
        {src: require('@/assets/recommendBooks/07.jpg'), title: '看成败，人生豪迈，只不过是孤独终老', content: '我把《如何孤独终老》这本书推荐给我两个朋友，老白和大刘。 他们二人都是单身，老白是离婚不久，发誓要孤独终老。大刘自从多年前得了相亲抑郁症以后，义无反顾的决定“只有孤独才是最安全的”。 过年的时候，我们三个人小聚了一下，我从怀里掏出《如何孤独终老》丢在饭桌上，...', time: '2022-03-04', author: '自然'},
        {src: require('@/assets/recommendBooks/08.jpg'), title: '通过电影去爱：战后二十年法国迷影文化史的坚实书写', content: '《迷影》一书确如译者蔡文晟先生在译后记中所感慨的，“简体版在它年满18岁时姗姗来迟”，对中文读者、影迷来说，这是一本迟到了起码有十年的珍贵书籍。在如今这样一个主倡短视频、流媒体，剧集当道的隔离时代，若还要念兹在兹地缅怀一种视电影、影院、纸媒为圣物的迷影文化，...', time: '2022-03-05', author: 'LOOK'},
        {src: require('@/assets/recommendBooks/09.jpg'), title: '小说中的科幻元素 带你憧憬未来', content: '《野未来》这本书的封面有两处看点吸引着我，一是区别与其他书籍的竖封腰；另一个是封腰上作家阿乙的推荐词：“我总是把王威廉的作品当作一个风向标去研究”。“风向标”！嗬，王威廉的作品能引领某类型写作的潮流，这可不是一般的高评！这两个信息足以引起我一睹为快的欲望，...', time: '2022-03-06', author: '苏苏的书屋'},
        {src: require('@/assets/recommendBooks/10.jpg'), title: '自知 是挖掘自身潜力的法宝', content: '曾经有位作家朋友告诉我，她常常会在睡梦中获得一些非常精彩的佳句，仿佛神来之笔，只是这样的句子必须要快速记录下来，否则就会很快忘记，为此，她在枕边放了纸笔。“日有所思夜有所梦”？可是如此整夜“折腾”，朋友会不会因此患上忧郁或是精神方面等疾病呢？ 读完著名心理学...', time: '2022-03-07', author: '苏苏的书屋'}
      ]
    }
  }
}
</script>

<style scoped>
.box {
  border-radius: 10px;
  border: black;
  width: 700px;
}

div > h3, p {
  text-align: left;
}

.color-gray {
  color: #909399;
}

.space {
  margin-right: 20px;
}
</style>