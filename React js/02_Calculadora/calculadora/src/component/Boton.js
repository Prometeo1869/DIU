export default function Boton ({clickHandle, name}) {

    const handleClick = () => clickHandle(name)

    return (
        <button onClick={handleClick}>{name}</button>
    );
        
        
}