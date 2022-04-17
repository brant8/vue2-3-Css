export default {
    template:`
      <div>
        <h3>{{ message }}</h3>
      </div>
    `,
    data(){ //组件的data表述得用返回
        return {
            message:"hello",
        }
    }
}