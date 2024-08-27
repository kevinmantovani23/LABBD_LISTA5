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

CREATE PROCEDURE sp_entradasaida(@tipo CHAR(1), @codigo_transacao INT, @codigo_produto INT, @quantidade INT, @saida VARCHAR(200) OUTPUT)
AS
	DECLARE @valor_total DECIMAL(7,2)
	DECLARE @queryProd VARCHAR(200)
	DECLARE @erro VARCHAR(200)
IF (LOWER(@tipo) = 's')
BEGIN
	BEGIN TRY
		SET @valor_total = (SELECT valor FROM produto WHERE codigo = @codigo_produto)
		IF (@valor_total IS NOT NULL)
		BEGIN
			SET @valor_total = @valor_total * @quantidade
		END ELSE
		BEGIN
			SET @valor_total = 1
		END
		SET @queryProd = 'INSERT INTO saida VALUES (''' + CAST(@codigo_transacao AS VARCHAR(3)) + ''', ''' +
							CAST(@codigo_produto AS VARCHAR(3)) + ''', '''+ CAST(@quantidade AS VARCHAR(3))
					+ ''', ''' + CAST(@valor_total AS VARCHAR(8)) + ''')'
		EXEC(@queryProd)
		PRINT(@queryProd)
	END TRY
		
	BEGIN CATCH
		SET @erro = ERROR_MESSAGE()
	END CATCH
	
END ELSE IF (LOWER(@tipo) = 'e')
BEGIN
	BEGIN TRY
		SET @valor_total = (SELECT valor FROM produto WHERE codigo = @codigo_produto)
		IF (@valor_total IS NOT NULL)
		BEGIN
			SET @valor_total = @valor_total * @quantidade
		END ELSE
		BEGIN
			SET @valor_total = 1
		END
		SET @queryProd = 'INSERT INTO entrada VALUES (''' + CAST(@codigo_transacao AS VARCHAR(3)) + ''', ''' +
							CAST(@codigo_produto AS VARCHAR(3)) + ''', '''+ CAST(@quantidade AS VARCHAR(3))
					+ ''', ''' + CAST(@valor_total AS VARCHAR(8)) + ''')'
		EXEC(@queryProd)
		PRINT(@queryProd)
	END TRY

	BEGIN CATCH
		SET @erro = ERROR_MESSAGE()
	END CATCH

END
ELSE
BEGIN
	PRINT('Opção inválida')
END
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


	