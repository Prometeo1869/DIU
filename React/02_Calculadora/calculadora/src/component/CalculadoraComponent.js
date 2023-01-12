import Boton from "./Boton"

export default function CalculadoraComponent({clickHandle}) {

    const handleClick = buttonName => clickHandle(buttonName)

    return (
        <div>
            <table>
                <tr>
                    <td>
                        <Boton name="AC" clickHandle={handleClick}></Boton>
                    </td>
                    <td>
                        <Boton name="+/-" clickHandle={handleClick}></Boton>
                    </td>
                    <td>
                        <Boton name="%" clickHandle={handleClick}></Boton>
                    </td>
                    <th>
                        <Boton name="÷" clickHandle={handleClick}></Boton>
                    </th>
                </tr>
                <tr>
                    <td>
                        <Boton name="7" clickHandle={handleClick}></Boton>
                    </td>
                    <td>
                        <Boton name="8" clickHandle={handleClick}></Boton>
                    </td>
                    <td>
                        <Boton name="9" clickHandle={handleClick}></Boton>
                    </td>
                    <th>
                        <Boton name="x" clickHandle={handleClick}></Boton>
                    </th>
                </tr>
                <tr>
                    <td>
                        <Boton name="4" clickHandle={handleClick}></Boton>
                    </td>
                    <td>
                        <Boton name="5" clickHandle={handleClick}></Boton>
                    </td>
                    <td>
                        <Boton name="6" clickHandle={handleClick}></Boton>
                    </td>
                    <th>
                        <Boton name="-" clickHandle={handleClick}></Boton>
                    </th>
                </tr>
                <tr>
                    <td>
                        <Boton name="1" clickHandle={handleClick}></Boton>
                    </td>
                    <td>
                        <Boton name="2" clickHandle={handleClick}></Boton>
                    </td>
                    <td>
                        <Boton name="3" clickHandle={handleClick}></Boton>
                    </td>
                    <th>
                        <Boton name="+" clickHandle={handleClick}></Boton>
                    </th>
                </tr>
                <tr>
                    <td colspan="2">
                        <Boton name="0" clickHandle={handleClick}></Boton>
                    </td>
                    <td>
                        <Boton name="." clickHandle={handleClick}></Boton>
                    </td>
                    <th>
                        <Boton name="=" clickHandle={handleClick}></Boton>
                    </th>
                </tr>
            </table>
        </div>
    );
}