CREATE DATABASE vendas

CREATE TABLE produto (
	codigo		INT,
	nome		VARCHAR(100),
	valor		DECIMAL(7,2),
	PRIMARY KEY (codigo)
)

CREATE TABLE entrada (
	codigo_transacao	INT,
	codigo_produto		INT	,
	quantidade			INT,
	valor_total			DECIMAL(7,2)
	PRIMARY KEY (codigo_transacao)
	FOREIGN KEY (codigo_produto) REFERENCES produto(codigo)
)

CREATE TABLE saida (
	codigo_transacao	INT,
	codigo_produto		INT	,
	quantidade			INT,
	valor_total			DECIMAL(7,2)
	PRIMARY KEY (codigo_transacao)
	FOREIGN KEY (codigo_produto) REFERENCES produto(codigo)
)
INSERT INTO produto VALUES(1, 'chocolate', 12.40)
SELECT * FROM produto
SELECT * FROM entrada
SELECT * FROM saida

CREATE PROCEDURE sp_entradasaida(@sqlfunc CHAR(1), @tipo VARCHAR(7), @codigo_transacao INT, @codigo_produto INT, @quantidade INT, @saida VARCHAR(200) OUTPUT)
AS
	DECLARE @valor_total DECIMAL(7,2)
	DECLARE @queryProd VARCHAR(200)
	DECLARE @erro VARCHAR(200)
IF (LOWER(@tipo) = 's')
BEGIN
	SET @tipo = 'saida' 
END ELSE IF (LOWER(@tipo) = 'e')
BEGIN
	SET @tipo = 'entrada'
END
ELSE
BEGIN
	SET @tipo = NULL
	RAISERROR('Opção inválida', 16, 1)
END

IF(@sqlfunc = 'i' AND @tipo IS NOT NULL)
BEGIN
	EXEC sp_insertentradasaida @tipo, @codigo_transacao, @codigo_produto, @quantidade, @saida OUT
END

IF(@sqlfunc = 'u' AND @tipo IS NOT NULL)
BEGIN
	EXEC sp_updateentradasaida @tipo, @codigo_transacao, @codigo_produto, @quantidade, @saida OUT
END

IF(@sqlfunc = 'd' AND @tipo IS NOT NULL)
BEGIN
	
END


CREATE PROCEDURE sp_deleteentradasaida(@tipo VARCHAR(7), @codigo_transacao INT)
AS
	DECLARE @queryProd VARCHAR(200)
	DECLARE @erro VARCHAR(200)
	BEGIN TRY
	SET @queryProd = 'DELETE FROM ' + @tipo + 'WHERE codigo_transacao = ' + CAST(@codigo_transacao AS VARCHAR(3))
	END TRY
		
	BEGIN CATCH
		SET @erro = ERROR_MESSAGE()
	END CATCH
	EXEC sp_errorhandler @erro

CREATE PROCEDURE sp_updateentradasaida(@tipo VARCHAR(7), @codigo_transacao INT, @codigo_produto INT, @quantidade INT, @saida VARCHAR(200) OUTPUT)
AS
	DECLARE @valor_total DECIMAL(7,2)
	DECLARE @queryProd VARCHAR(200)
	DECLARE @erro VARCHAR(200)
	BEGIN TRY
		SET @valor_total = (SELECT valor FROM produto WHERE codigo = @codigo_produto)
		IF (@valor_total IS NOT NULL)
		BEGIN
			SET @valor_total = @valor_total * @quantidade
		END ELSE
		BEGIN
			SET @valor_total = 1
		END
		SET @queryProd = 'UPDATE ' + @tipo + 
						 ' SET codigo_transacao = ' + CAST(@codigo_transacao AS VARCHAR(3)) + ', codigo_produto = ' +
							CAST(@codigo_produto AS VARCHAR(3)) + ', quantidade = '+ CAST(@quantidade AS VARCHAR(3))
					+ ', valor_total = ' + CAST(@valor_total AS VARCHAR(8)) + ' WHERE codigo_transacao = ' + CAST(@codigo_transacao AS VARCHAR(3))
		EXEC(@queryProd)
		PRINT(@queryProd)
	END TRY
		
	BEGIN CATCH
		SET @erro = ERROR_MESSAGE()
	END CATCH
	EXEC sp_errorhandler @erro
	
CREATE PROCEDURE sp_insertentradasaida (@tipo VARCHAR(7), @codigo_transacao INT, @codigo_produto INT, @quantidade INT, @saida VARCHAR(200) OUTPUT)
AS
	DECLARE @valor_total DECIMAL(7,2)
	DECLARE @queryProd VARCHAR(200)
	DECLARE @erro VARCHAR(200)
	BEGIN TRY
		SET @valor_total = (SELECT valor FROM produto WHERE codigo = @codigo_produto)
		IF (@valor_total IS NOT NULL)
		BEGIN
			SET @valor_total = @valor_total * @quantidade
		END ELSE
		BEGIN
			SET @valor_total = 1
		END
		SET @queryProd = 'INSERT INTO ' + @tipo + ' VALUES (''' + CAST(@codigo_transacao AS VARCHAR(3)) + ''', ''' +
							CAST(@codigo_produto AS VARCHAR(3)) + ''', '''+ CAST(@quantidade AS VARCHAR(3))
					+ ''', ''' + CAST(@valor_total AS VARCHAR(8)) + ''')'
		EXEC(@queryProd)
		PRINT(@queryProd)
	END TRY
		
	BEGIN CATCH
		SET @erro = ERROR_MESSAGE()
	END CATCH
	EXEC sp_errorhandler @erro

	CREATE PROCEDURE sp_errorhandler(@erro VARCHAR(200))
	AS
	IF(@erro IS NOT NULL)
		BEGIN
			IF (@erro LIKE '%FOREIGN KEY%')
			BEGIN
				SET @erro = 'Produto não cadastrado'
			END ELSE IF (@erro LIKE '%PRIMARY KEY%')
			BEGIN
				SET @erro = 'Código de transacao já existente'
			END
			PRINT(@erro)
			RAISERROR(@erro, 16, 1)
		END


	
