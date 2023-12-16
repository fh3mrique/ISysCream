import "./styles.css"
import imgSorvete from "../../assets/imgs/sorvete.png"

const Home = () => {
  return (
    <div className="home-container">
        <div className="home-text-container">
            <h1>
                GERENCIE SUA <br /> FRANQUIA DE <br />
                <span>SOVERTERIA</span>
            </h1>

            <div className="btn-home">
                <button>Acesse</button>
            </div>
        </div>

        <div className="img-home-container">
            <img src={imgSorvete} alt="" />
        </div>
    </div>
  )
}

export default Home