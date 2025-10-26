.PHONY: init


init:
	@git add . && \
	git commit -m "cs61b" && \
	git push origin main
