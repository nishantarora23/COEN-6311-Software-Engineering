import PropTypes from 'prop-types'

function Header({text, bgColor, textColor}) {
  const headerstyles = {
    backgroundColor: bgColor,
    color: textColor
  }
  
  
  return (
    <header style={headerstyles}>
      <div className = 'container'>
        <h2>{text}</h2>
      </div>
    </header>
  )
}

Header.defaultProps = {
  text: 'Job-Hive',
  bgColor: 'rgba(70,130,180)',
  textColor: '#FFFFFF'
}

Header.propTypes = {
  text: PropTypes.string,
  bgColor: PropTypes.string,
  textColor: PropTypes.string
}


export default Header
