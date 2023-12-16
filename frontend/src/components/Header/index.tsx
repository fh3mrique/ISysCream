import "./style.css"
import SorveteLogo from "../../assets/imgs/sorveteLogo.svg";

const Header = () => {
  return (
    <div className="header-container">
        <img src={SorveteLogo} alt="" />
        <header>
            <h1>ISys<span>Cream</span></h1>
        </header>
    </div>
  )
}

export default Header