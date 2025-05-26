-- Crear tabla de auditoría para registrar cambios
CREATE TABLE `auditoria` (
  `id_auditoria` INT AUTO_INCREMENT PRIMARY KEY,
  `tabla` VARCHAR(50) NOT NULL,
  `accion` VARCHAR(50) NOT NULL,
  `detalle` TEXT NOT NULL,
  `fecha` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Trigger para validar precio y stock antes de INSERT en productos
DELIMITER //
CREATE TRIGGER `before_productos_insert`
BEFORE INSERT ON `productos`
FOR EACH ROW
BEGIN
  IF NEW.precio < 0 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'El precio no puede ser negativo';
  END IF;
  IF NEW.stock < 0 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'El stock no puede ser negativo';
  END IF;
END //
DELIMITER ;

-- Trigger para registrar cambios en el stock después de UPDATE en productos
DELIMITER //
CREATE TRIGGER `after_productos_update`
AFTER UPDATE ON `productos`
FOR EACH ROW
BEGIN
  IF OLD.stock != NEW.stock THEN
    INSERT INTO `auditoria` (`tabla`, `accion`, `detalle`)
    VALUES (
      'productos',
      'UPDATE',
      CONCAT('Producto ID: ', NEW.id_producto, 
             ', Nombre: ', NEW.nombre, 
             ', Stock anterior: ', OLD.stock, 
             ', Stock nuevo: ', NEW.stock)
    );
  END IF;
END //
DELIMITER ;

-- Trigger para validar email antes de INSERT en usuarios
DELIMITER //
CREATE TRIGGER `before_usuarios_insert`
BEFORE INSERT ON `usuarios`
FOR EACH ROW
BEGIN
  IF NEW.email NOT LIKE '%@%.%' THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'El email debe contener "@" y un dominio válido';
  END IF;
END //
DELIMITER ;

-- Trigger para registrar nuevos usuarios después de INSERT en usuarios
DELIMITER //
CREATE TRIGGER `after_usuarios_insert`
AFTER INSERT ON `usuarios`
FOR EACH ROW
BEGIN
  INSERT INTO `auditoria` (`tabla`, `accion`, `detalle`)
  VALUES (
    'usuarios',
    'INSERT',
    CONCAT('Usuario ID: ', NEW.id_usuario, 
           ', Nombre de usuario: ', NEW.nombre_usuario, 
           ', Email: ', NEW.email)
  );
END //
DELIMITER ;